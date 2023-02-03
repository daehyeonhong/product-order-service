package com.example.productorderservice.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(value = MockitoExtension.class)
class ProductServiceTest {
    private ProductPort productPort;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        this.productPort = Mockito.mock(ProductPort.class);
        this.productService = new ProductService(productPort);
    }

    @Test
    void 상품수정() {
        final Long productId = 1L;
        final String afterName = "상품수정";
        final int afterPrice = 2_000;
        final UpdateProductRequest request = new UpdateProductRequest(afterName, afterPrice, DiscountPolicy.NONE);
        final int beforePrice = 1_000;
        final String beforeName = "상품평";
        final Product product = new Product(beforeName, beforePrice, DiscountPolicy.NONE);
        Mockito.when(productPort.getProduct(productId)).thenReturn(product);

        this.productService.updateProduct(productId, request);

        Assertions.assertEquals(product.getName(), afterName);
        Assertions.assertEquals(product.getPrice(), afterPrice);
        Assertions.assertNotEquals(product.getName(), beforeName);
        Assertions.assertNotEquals(product.getPrice(), beforePrice);
    }

}
