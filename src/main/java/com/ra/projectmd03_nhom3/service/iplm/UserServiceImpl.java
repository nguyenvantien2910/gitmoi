package com.ra.projectmd03_nhom3.service.iplm;

import com.ra.projectmd03_nhom3.constant.RoleName;
import com.ra.projectmd03_nhom3.dao.IRoleDao;
import com.ra.projectmd03_nhom3.dao.IUserDao;
import com.ra.projectmd03_nhom3.dto.request.FormLogin;
import com.ra.projectmd03_nhom3.dto.request.FormRegister;
import com.ra.projectmd03_nhom3.model.Roles;
import com.ra.projectmd03_nhom3.model.Users;
import com.ra.projectmd03_nhom3.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Override
    public Users login(FormLogin formLogin) {
        return userDao.login(formLogin.getUsername(), formLogin.getPassword());
    }

    @Override
    public boolean register(FormRegister formRegister) {
        Set<Roles> roles = new HashSet<>();
        roles.add(roleDao.findByRoleName(RoleName.ROLE_USER));
        Users users = Users.builder()
                .fullName(formRegister.getFullName())
                .username(formRegister.getUsername())
                .password(formRegister.getPassword())
                .roles(roles)
                .status(true)
                .build();
        return userDao.register(users);
    }
}
