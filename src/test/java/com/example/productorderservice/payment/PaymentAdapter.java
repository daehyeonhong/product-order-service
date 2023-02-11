package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;

class PaymentAdapter implements PaymentPort {
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    PaymentAdapter(final PaymentGateway paymentGateway, final PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Order getOrder(final Long orderId) {
        return new Order(new Product("상품명", 1_000, DiscountPolicy.NONE), 3);
    }

    @Override
    public void pay(final int totalPrice, final String cardNumber) {
        this.paymentGateway.execute(totalPrice,cardNumber);
    }

    @Override
    public void save(final Payment payment) {
        this.paymentRepository.save(payment);
    }
}
