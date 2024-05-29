package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Order;

import java.util.List;

public interface IOrderDAO {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    boolean addOrder(Order order);
    boolean updateOrder(Order order);

}
