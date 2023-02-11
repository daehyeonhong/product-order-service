package com.example.productorderservice.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {
    private PaymentService paymentService;
    private PaymentPort paymentPort;

    @BeforeEach
    void setUp() {
        final PaymentGateway paymentGateway = new ConsolePaymentGateway();
        final PaymentRepository paymentRepository = new PaymentRepository();
        this.paymentPort = new PaymentAdapter(paymentGateway, paymentRepository);
        this.paymentService = new PaymentService(this.paymentPort);
    }

    @Test
    void 상품주문() {
        final PaymentRequest request = PaymentSteps.주문결제요청_생성();
        this.paymentService.payment(request);
    }
}
