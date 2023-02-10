package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class PaymentServiceTest {
    private PaymentService paymentService;
    private PaymentPort paymentPort;

    @BeforeEach
    void setUp() {
        final PaymentGateway paymentGateway=new ConsolePaymentGateway();
        final PaymentRepository paymentRepository= new PaymentRepository();
        this.paymentPort = new PaymentAdapter(paymentGateway, paymentRepository);
        this.paymentService = new PaymentService(this.paymentPort);
    }

    @Test
    void 상품주문() {
        final Long orderId = 1L;
        final String cardNumber = "1234-1234-1234-1234";
        final PaymentRequest request = new PaymentRequest(orderId, cardNumber);
        this.paymentService.payment(request);
    }

    private record PaymentRequest(Long orderId, String cardNumber) {
        private PaymentRequest {
            Assert.notNull(orderId, "주문 ID는 필수입니다.");
            Assert.hasText(cardNumber, "카드 번호는  필수입니다.");
        }
    }

    private class PaymentService {
        private final PaymentPort paymentPort;

        private PaymentService(final PaymentPort paymentPort) {
            this.paymentPort = paymentPort;
        }

        public void payment(final PaymentRequest request) {
            final Order order = this.paymentPort.getOrder(request.orderId());
            final Payment payment = new Payment(order, request.cardNumber());
            this.paymentPort.pay(payment);
            this.paymentPort.save(payment);
        }
    }

    private interface PaymentPort {
        Order getOrder(Long orderId);

        void pay(Payment payment);

        void save(Payment payment);
    }

    private class Payment {
        public Long getId() {
            return id;
        }

        private Long id;
        private final Order order;
        private final String cardNumber;

        public Payment(final Order order, final String cardNumber) {
            Assert.notNull(order, "주문은 필수입니다.");
            Assert.hasText(cardNumber, "카드 번호는  필수입니다.");
            this.order = order;
            this.cardNumber = cardNumber;
        }

        public void assignId(final long id) {
            this.id = id;
        }
    }

    private class PaymentAdapter implements PaymentPort {
        private final PaymentGateway paymentGateway;
        private final PaymentRepository paymentRepository;

        private PaymentAdapter(final PaymentGateway paymentGateway, final PaymentRepository paymentRepository) {
            this.paymentGateway = paymentGateway;
            this.paymentRepository = paymentRepository;
        }

        @Override
        public Order getOrder(final Long orderId) {
            return new Order(new Product("상품명", 1_000, DiscountPolicy.NONE), 3);
        }

        @Override
        public void pay(final Payment payment) {
            this.paymentGateway.execute(payment);
        }

        @Override
        public void save(final Payment payment) {
            this.paymentRepository.save(payment);
        }
    }

    private interface PaymentGateway {
        void execute(Payment payment);
    }

    public class ConsolePaymentGateway implements PaymentGateway {
        @Override
        public void execute(final Payment payment) {
            System.out.println("결제완료.");
        }
    }

    private class PaymentRepository {
        private final Map<Long, Payment> persistence = new ConcurrentHashMap<>();
        private final AtomicLong sequence = new AtomicLong();

        public void save(final Payment payment) {
            payment.assignId(sequence.addAndGet(1));
            this.persistence.put(payment.getId(), payment);
        }
    }
}
