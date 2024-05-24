package com.ra.projectmd03_nhom3.dao;

import com.ra.projectmd03_nhom3.model.Users;

public interface IUserDao {
    boolean register(Users users);
    Users login(String username, String password);
}
