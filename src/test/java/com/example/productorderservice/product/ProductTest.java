package com.example.productorderservice.product;

import com.example.productorderservice.product.domain.DiscountPolicy;
import com.example.productorderservice.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void update() throws Exception {
        //given
        final Product product = new Product("상품명", 1_000, DiscountPolicy.NONE);
        //when
        product.update("상품수정", 2_000, DiscountPolicy.NONE);
        //then
        Assertions.assertEquals(product.getName(), "상품수정");
        Assertions.assertEquals(product.getPrice(), 2_000);
        Assertions.assertEquals(product.getDiscountPolicy(), DiscountPolicy.NONE);
    }

    @Test
    void noneDiscountedProduct() {
        final Product product = new Product("상품명", 1_000, DiscountPolicy.NONE);
        Assertions.assertEquals(product.getDiscountPrice(), 1_000);
    }

    @Test
    void fix1000DiscountedProduct() {
        final Product product = new Product("상품명", 1_000, DiscountPolicy.FIX_1000_AMOUNT);
        Assertions.assertEquals(product.getDiscountPrice(), 0);
    }

    @Test
    void overFix1000DiscountedProduct() {
        final Product product = new Product("상품명", 800, DiscountPolicy.FIX_1000_AMOUNT);
        Assertions.assertEquals(product.getDiscountPrice(), 0);
    }
}
