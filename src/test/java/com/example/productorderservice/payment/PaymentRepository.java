package com.example.productorderservice.payment;

import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class PaymentRepository {
    private final Map<Long, Payment> persistence = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public void save(final Payment payment) {
        payment.assignId(sequence.addAndGet(1));
        this.persistence.put(payment.getId(), payment);
    }
}
