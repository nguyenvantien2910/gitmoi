package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Product;

import java.util.List;

public interface IProductServiceUser {
    List<Product> findAll();
    Product findById(Long id);
    void saveOrUpdate(Product product);
    void deleteById(Long id);
    List<Product> findByCategoryId(Long categoryId);
}
