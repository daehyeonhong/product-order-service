package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품등록() {
        //given
        final var request = ProductSteps.상품등록요청_생성();
        //when
        final var response = ProductSteps.상품등록요청(request);
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회() {
        final AddProductRequest addProductRequest = ProductSteps.상품등록요청_생성();
        ProductSteps.상품등록요청(addProductRequest);
        final Long productId = 1L;

        final var response = ProductSteps.상품조회요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo(addProductRequest.name());
    }

    @Test
    void 상품수정() throws Exception {
        //given
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        //when

        //then
        final long productId = 1L;
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ProductSteps.상품수정요청_생성("상품 수정", 2_000))
                .when()
                .patch("/products/{productId}", productId)
                .then()
                .log().all().extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(this.productRepository.findById(productId).get().getName()).isEqualTo("상품 수정");
    }

}
