package com.example.productorderservice.order;

import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    void getOrderNoneTotal() {
        final Product product = new Product("상품명", 1_000, DiscountPolicy.NONE);
        final Order order = new Order(product, 2);

        Assertions.assertEquals(order.getTotalPrice(), 2_000);
    }

    @Test
    void getOrderFix1000Total() {
        final Product product = new Product("상품명", 2_000, DiscountPolicy.FIX_1000_AMOUNT);
        final Order order = new Order(product, 2);

        Assertions.assertEquals(order.getTotalPrice(), 2_000);
    }

    @Test
    void getOrderOverFix1000Total() {
        final Product product = new Product("상품명", 700, DiscountPolicy.FIX_1000_AMOUNT);
        final Order order = new Order(product, 2);

        Assertions.assertEquals(order.getTotalPrice(), 0);
    }
}
