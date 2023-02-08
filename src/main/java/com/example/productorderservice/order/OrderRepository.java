package com.example.productorderservice.order;

import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class OrderRepository {
    private final Map<Long, Order> persistence = new ConcurrentHashMap<>();
    private AtomicLong sequence = new AtomicLong();

    public void save(final Order order) {
        order.assignId(sequence.incrementAndGet());
        persistence.put(order.getId(), order);
    }
}
