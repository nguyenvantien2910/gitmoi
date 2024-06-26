package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.dto.request.ProductRequest;
import com.ra.projectmd03_nhom4.model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> getAllProducts(int currentPage,int size);
    List<Product> getAllProducts();
    List<Product> findByName(String name);
    Product findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    String getImageByProductId(Long id);
    Long countAllProduct();
    List<Product> searchProduct(String name);
}
