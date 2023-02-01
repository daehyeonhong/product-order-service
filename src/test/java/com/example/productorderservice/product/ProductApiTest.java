package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ProductApiTest extends ApiTest {
    private final ProductSteps productSteps = new ProductSteps();

    @Test
    void 상품등록() throws Exception {
        //given
        final var request = productSteps.상품등록요청_생성();
        final var response = ProductSteps.상품등록요청(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        //when

        //then
    }

    public AddProductRequest 상품등록요청_생성() {
        return productSteps.상품등록요청_생성();
    }
}
