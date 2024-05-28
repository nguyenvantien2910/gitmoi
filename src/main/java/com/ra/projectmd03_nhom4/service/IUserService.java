package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.dto.request.FromAddUser;
import com.ra.projectmd03_nhom4.model.User;

public interface IUserService extends IService<User,Integer,String,Boolean,Long> {
    User login(FormLogin formLogin);
    boolean register(FormRegister formRegister);
    boolean addNewAdmin(FromAddUser fromAddUser);
}
