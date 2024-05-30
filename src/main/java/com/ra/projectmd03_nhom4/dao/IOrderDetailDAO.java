package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.OrderDetail;

import java.util.List;

public interface IOrderDetailDAO {
    void save(OrderDetail orderDetail);
    List<OrderDetail> getOrderDetail(Long orderId);
}
