package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Category;

import java.util.List;

public interface ICategoryDao{
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void block(Long id);

    boolean checkCategoryName(String categoryName);
}
