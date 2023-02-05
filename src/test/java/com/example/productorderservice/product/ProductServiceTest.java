package com.example.productorderservice.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void 상품수정() {
        this.productService.addProduct(ProductSteps.상품등록요청_생성());

        final Long productId = 1L;
        final String afterName =  "상품수정";
        final int afterPrice = 2_000;
        final int beforePrice = 1_000;
        final String beforeName = "상품평";
        final UpdateProductRequest request = ProductSteps.상품수정요청_생성(afterName, afterPrice);

        this.productService.updateProduct(productId, request);

        final ResponseEntity<GetProductResponse> response = this.productService.getProduct(productId);
        final GetProductResponse productResponse = response.getBody();
        Assertions.assertEquals(productResponse.name(), afterName);
        Assertions.assertEquals(productResponse.price(), afterPrice);
        Assertions.assertNotEquals(productResponse.name(), beforeName);
        Assertions.assertNotEquals(productResponse.price(), beforePrice);
    }
}
