package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class ProductApiTest extends ApiTest {
    @Test
    void 상품등록() throws Exception {
        //given
        final var request = this.상품등록요청_생성();
        final var response = this.상품등록요청(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        //when

        //then
    }

    private static ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
    }

    private AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1_000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }
}
