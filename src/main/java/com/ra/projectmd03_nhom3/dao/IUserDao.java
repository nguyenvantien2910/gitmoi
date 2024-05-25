package com.ra.projectmd03_nhom3.dao;

import com.ra.projectmd03_nhom3.model.User;

public interface IUserDao {
    boolean register(User user);
    User login(String username, String password);
}
