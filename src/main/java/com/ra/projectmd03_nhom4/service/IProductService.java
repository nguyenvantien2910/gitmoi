package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.dto.request.ProductRequest;
import com.ra.projectmd03_nhom4.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll(int currentPage, int page);
    Product findById(Long id);
    List<Product> getAllProducts();
    List<Product> findByName(String name);

    void saveOrUpdate(ProductRequest productRequest);

    void deleteById(Long id);

    String getImageByProductId(Long id);
    Long countProduct();
    List<Product> searchProduct(String name);
}
