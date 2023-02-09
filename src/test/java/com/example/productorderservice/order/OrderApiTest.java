package com.example.productorderservice.order;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderApiTest extends ApiTest {

    @Test
    void 상품주문() {
        //given
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        //when
        final CreateOrderRequest request = OrderSteps.상품주문요청_생성();
        //then
        final var response = OrderSteps.상품주문요청(request);

        Assertions.assertEquals(response.statusCode(), HttpStatus.CREATED.value());
    }

}
