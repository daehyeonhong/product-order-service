package com.example.productorderservice.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyTests {

    @Test
    void applyDiscount() {
        final int none = DiscountPolicy.NONE.applyDiscount(1_500);
        Assertions.assertEquals(none, 1_500);
        final int price_FIX_2_000 = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(2_000);
        Assertions.assertEquals(price_FIX_2_000, 1_000);
        final int price_FIX_1_000 = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(1_000);
        Assertions.assertEquals(price_FIX_1_000, 0);
        final int price_FIX_9000 = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(9_000);
        Assertions.assertEquals(price_FIX_9000, 8_000);
    }
}
