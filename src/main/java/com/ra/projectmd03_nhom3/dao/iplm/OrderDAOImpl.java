package com.ra.projectmd03_nhom3.dao.iplm;

import com.ra.projectmd03_nhom3.dao.IDAO;
import com.ra.projectmd03_nhom3.model.Order;

import java.util.List;

public class OrderDAOImpl implements IDAO<Order,Integer,String,Boolean,Long> {

    @Override
    public List<Order> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        return List.of();
    }

    @Override
    public Long count(String searchQuery) {
        return null;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public boolean add(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean updateStatus(Integer id, Boolean newStatus) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
