package com.example.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {
    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        this.productRepository = new ProductRepository();
        this.productPort = new ProductAdapter(this.productRepository);
        this.productService = new ProductService(this.productPort);
    }

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
