package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;

class PaymentService {
    private final PaymentPort paymentPort;

    PaymentService(final PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    public void payment(final PaymentRequest request) {
        final Order order = this.paymentPort.getOrder(request.orderId());
        final Payment payment = new Payment(order, request.cardNumber());
        this.paymentPort.pay(order.getTotalPrice(), payment.getCardNumber());
        this.paymentPort.save(payment);
    }
}
