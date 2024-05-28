package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.iplm.OrderDAOImpl;
import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderDAOImpl orderDAO;

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderDAO.getOrdersByUserId(userId);
    }

    @Override
    public boolean addOrder(Order order) {
        return orderDAO.addOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }
}
