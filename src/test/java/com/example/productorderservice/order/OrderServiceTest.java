package com.example.productorderservice.order;

import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import com.example.productorderservice.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class OrderServiceTest {
    private OrderService orderService;
    private OrderPort orderPort;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        orderPort = new OrderPort() {
            @Override
            public Product getProductById(final Long productId) {
                return new Product("상품명", 1_000, DiscountPolicy.NONE);
            }

            @Override
            public void save(final Order order) {
                orderRepository.save(order);
            }
        };
        this.orderService = new OrderService(orderPort);
    }

    @Test
    void 상품주문() throws Exception {
        //given
        final Long productId = 1L;
        final int quantity = 2;
        final CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        this.orderService.createOrder(request);
        //when

        //then
    }

    private record CreateOrderRequest(Long productId, int quantity) {
        private CreateOrderRequest {
            Assert.notNull(productId, "상품 ID는 필수입니다.");
            Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
        }
    }

    private static class OrderService {
        private final OrderPort orderPort;

        private OrderService(final OrderPort orderPort) {
            this.orderPort = orderPort;
        }

        public void createOrder(final CreateOrderRequest request) {
            final Product product = this.orderPort.getProductById(request.productId);
            final Order order = new Order(product, request.quantity);

            this.orderPort.save(order);
        }
    }

    private static class Order {
        private long id;
        private final Product product;
        private final int quantity;

        private Order(final Product product, final int quantity) {
            Assert.notNull(product, "상품은 필수입니다.");
            Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
            this.product = product;
            this.quantity = quantity;
        }

        public void assignId(final long id) {
            this.id = id;
        }

        public Long getId() {
            return this.id;
        }
    }

    private interface OrderPort {
        Product getProductById(Long productId);

        void save(Order order);
    }

    private class OrderAdapter implements OrderPort {
        private final ProductRepository productRepository;
        private final OrderRepository orderRepository;

        private OrderAdapter(final ProductRepository productRepository, final OrderRepository orderRepository) {
            this.productRepository = productRepository;
            this.orderRepository = orderRepository;
        }

        @Override
        public Product getProductById(final Long productId) {
            return this.productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException(""));
        }

        @Override
        public void save(final Order order) {
            this.orderRepository.save(order);
        }
    }

    private class OrderRepository {
        private final Map<Long, Order> persistence = new ConcurrentHashMap<>();
        private AtomicLong sequence = new AtomicLong();

        public void save(final Order order) {
            order.assignId(sequence.incrementAndGet());
            persistence.put(order.getId(), order);
        }
    }
}
