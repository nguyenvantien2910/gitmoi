package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.model.OrderDetail;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    boolean addOrder(Order order);
    boolean updateOrder(Order order);
    void cancelOrder(Long orderId);
    void acceptOrder(Long orderId);
    boolean orderCheckout(Order order);
    List<OrderDetail> findDetailByOrderId(Long orderId);
}
