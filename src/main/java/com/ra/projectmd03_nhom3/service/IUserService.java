package com.ra.projectmd03_nhom3.service;

import com.ra.projectmd03_nhom3.dto.request.FormLogin;
import com.ra.projectmd03_nhom3.dto.request.FormRegister;
import com.ra.projectmd03_nhom3.model.User;

public interface IUserService {
    User login(FormLogin formLogin);
    boolean register(FormRegister formRegister);
}
