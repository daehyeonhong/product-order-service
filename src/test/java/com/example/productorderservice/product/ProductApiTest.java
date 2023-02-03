package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {
    private final ProductSteps productSteps = new ProductSteps();

    @Test
    void 상품등록() {
        //given
        final var request = this.productSteps.상품등록요청_생성();
        //when
        final var response = ProductSteps.상품등록요청(request);
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회() {
        final AddProductRequest addProductRequest = this.productSteps.상품등록요청_생성();
        ProductSteps.상품등록요청(addProductRequest);
        final Long productId = 1L;

        final var response = ProductSteps.상품조회요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo(addProductRequest.name());
    }

}
