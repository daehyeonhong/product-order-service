package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void 상품등록() throws Exception {
        //given
        final AddProductRequest request = this.상품등록요청_생성();
        this.productService.addProduct(request);

        //when

        //then
    }

    private AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1_000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }
}
