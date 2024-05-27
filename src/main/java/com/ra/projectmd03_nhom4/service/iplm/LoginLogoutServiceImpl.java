package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.dao.IRoleDao;
import com.ra.projectmd03_nhom4.dao.IUserDao;
import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.model.Role;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class LoginLogoutServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Override
    public User login(FormLogin formLogin) {
        return userDao.login(formLogin.getUsername(), formLogin.getPassword());
    }

    @Override
    public boolean register(FormRegister formRegister) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findByRoleName(RoleName.ROLE_USER));
        User user = User.builder()
                .fullName(formRegister.getFullName())
                .username(formRegister.getUsername())
                .password(formRegister.getPassword())
                .address(formRegister.getAddress())
                .email(formRegister.getEmail())
                .createdAt(new Date())
                .roles(roles)
                .status(true)
                .build();
        return userDao.register(user);
    }
}
