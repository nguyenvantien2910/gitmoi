package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.dto.request.FromAddUser;
import com.ra.projectmd03_nhom4.model.User;

public interface IUserDao<U, I extends Number, S, B, L extends Number> extends IDAO<User,Integer,String,Boolean,Long> {
    boolean register(User user);
    User login(String username, String password);
    boolean addNewAdmin(User user);
}
