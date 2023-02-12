package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
class PaymentService {
    private final PaymentPort paymentPort;

    PaymentService(final PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    @PostMapping
    public ResponseEntity<Void> payment(@RequestBody final PaymentRequest request) {
        final Order order = this.paymentPort.getOrder(request.orderId());
        final Payment payment = new Payment(order, request.cardNumber());
        this.paymentPort.pay(order.getTotalPrice(), payment.getCardNumber());
        this.paymentPort.save(payment);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
