package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.model.User;

public interface IUserService {
    User login(FormLogin formLogin);
    boolean register(FormRegister formRegister);

    User findById(Long id);
}
