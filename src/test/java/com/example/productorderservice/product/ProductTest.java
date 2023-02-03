package com.example.productorderservice.product;

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

}
