package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.dao.ICategoryDao;
import com.ra.projectmd03_nhom4.model.Category;

import java.util.List;

public interface ICategoryService  {
    List<Category> findAll();
    Category findById(Long id);
    void saveOrUpdate(Category category);
    void block(Long id);

    boolean checkCategoryName(String categoryName);
//    boolean checkProductByCategoryId(int id);
}
