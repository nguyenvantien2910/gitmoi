package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.IDAO;
import com.ra.projectmd03_nhom4.model.Category;
import com.ra.projectmd03_nhom4.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceIplm implements IService<Category,Integer,String,Boolean,Long> {
    @Autowired
    IDAO<Category, Integer, String, Boolean, Long> categoryDao;


    @Override
    @Transactional
    public List<Category> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        return categoryDao.findAll(pageNo, pageSize, sortField, sortDirection, searchQuery);
    }

    @Override
    public Long count(String searchQuery) {
        return categoryDao.count(searchQuery);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public boolean add(Category category) {
        return categoryDao.add(category);
    }

    @Override
    public boolean update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public boolean updateStatus(Integer id, Boolean newStatus) {
        return categoryDao.updateStatus(id, newStatus);
    }

    @Override
    public boolean delete(Integer id) {
        return categoryDao.delete(id);
    }
}
