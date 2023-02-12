package com.example.productorderservice.payment;

import com.example.productorderservice.order.OrderService;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.product.ProductService;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @Test
    void 상품주문() {
        this.productService.addProduct(ProductSteps.상품등록요청_생성());
        this.orderService.createOrder(OrderSteps.상품주문요청_생성());
        final PaymentRequest request = PaymentSteps.주문결제요청_생성();
        this.paymentService.payment(request);
    }
}
