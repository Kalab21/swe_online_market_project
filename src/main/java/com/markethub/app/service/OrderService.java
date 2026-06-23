package com.markethub.app.service;

import com.markethub.app.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order saveOrder(Order order);
    void deleteOrderById(Long orderId);
    List<Order> getOrdersByOwnerUserId(Long userId);
}
