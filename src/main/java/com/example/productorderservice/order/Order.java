package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;
import org.springframework.util.Assert;

class Order {
    private final Product product;
    private final int quantity;
    private long id;

    Order(final Product product, final int quantity) {
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
