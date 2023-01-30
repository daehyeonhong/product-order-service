package com.example.productorderservice.product;

import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class ProductRepository {
    private final Map<Long, Product> persistence = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    public void save(final Product product) {
        product.assignId(++sequence);
        this.persistence.put(product.getId(), product);
    }
}
