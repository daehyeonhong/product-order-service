package com.example.productorderservice.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void 상품조회() throws Exception {
        //given
        this.productService.addProduct(ProductSteps.상품등록요청_생성());
        final Long productId = 1L;
        //when
        final GetProductResponse response = this.productService.getProduct(productId);

        //then
        Assertions.assertThat(response).isNotNull();
    }

}
