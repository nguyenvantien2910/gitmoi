package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    boolean addOrder(Order order);
    boolean updateOrder(Order order);
}
