package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.IDAO;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIplm implements IService<User,Integer,String,Boolean,Long> {
    @Autowired
    IDAO<User, Integer, String, Boolean, Long> userDao;

    @Override
    public List<User> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        return userDao.findAll(pageNo, pageSize, sortField, sortDirection, searchQuery);
    }

    @Override
    public Long count(String searchQuery) {
        return userDao.count(searchQuery);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public boolean add(User user) {
        return userDao.add(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean updateStatus(Integer id, Boolean newStatus) {
        return userDao.updateStatus(id, newStatus);
    }

    @Override
    public boolean delete(Integer id) {
        return userDao.delete(id);
    }
}
