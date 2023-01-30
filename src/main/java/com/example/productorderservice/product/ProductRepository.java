package com.example.productorderservice.product;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ProductRepository {
    private final Map<Long, Product> persistence = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    public void save(final Product product) {
        product.assignId(++sequence);
        this.persistence.put(product.getId(), product);
    }
}
