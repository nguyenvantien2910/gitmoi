package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.User;

public interface IUserDao {
    boolean register(User user);
    User login(String username, String password);
}
