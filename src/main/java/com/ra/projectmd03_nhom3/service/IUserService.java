package com.ra.projectmd03_nhom3.service;

import com.ra.projectmd03_nhom3.dto.request.FormLogin;
import com.ra.projectmd03_nhom3.dto.request.FormRegister;
import com.ra.projectmd03_nhom3.model.Users;

public interface IUserService {
    Users login(FormLogin formLogin);
    boolean register(FormRegister formRegister);
}
