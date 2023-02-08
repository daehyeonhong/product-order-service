package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;
import org.springframework.stereotype.Component;

@Component
class OrderService {
    private final OrderPort orderPort;

    public OrderService(final OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public void createOrder(final CreateOrderRequest request) {
        final Product product = this.orderPort.getProductById(request.productId());
        final Order order = new Order(product, request.quantity());

        this.orderPort.save(order);
    }
}
