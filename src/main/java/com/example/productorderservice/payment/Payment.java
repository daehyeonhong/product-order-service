package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import org.springframework.util.Assert;

class Payment {
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

    public Long getId() {
        return id;
    }

    public int getOrder() {
        return order.getTotalPrice();
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
