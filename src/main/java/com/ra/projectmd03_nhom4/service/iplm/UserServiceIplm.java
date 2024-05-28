package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.dao.IRoleDao;
import com.ra.projectmd03_nhom4.dao.IUserDao;
import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.dto.request.FromAddUser;
import com.ra.projectmd03_nhom4.model.Role;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceIplm implements IUserService {
    @Autowired
    private IRoleDao roleDao;

    @Autowired
    IUserDao<User, Integer, String, Boolean, Long> userDao;

    @Override
    public List<User> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        return userDao.findAll(pageNo, pageSize, sortField, sortDirection, searchQuery);
    }

    @Override
    public Long count(String searchQuery) {
        return userDao.count(searchQuery);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean add(User user) {
        return userDao.add(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean updateStatus(Long id, Boolean newStatus) {
        return userDao.updateStatus(id, newStatus);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

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
                .phone(formRegister.getPhone())
                .createdAt(new Date())
                .roles(roles)
                .status(true)
                .build();
        return userDao.register(user);
    }

    @Override
    public boolean addNewAdmin(FromAddUser fromAddUser) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findByRoleName(RoleName.ROLE_ADMIN));
        User user = User.builder()
                .fullName(fromAddUser.getFullName())
                .username(fromAddUser.getUsername())
                .password(fromAddUser.getPassword())
                .address(fromAddUser.getAddress())
                .phone(fromAddUser.getPhone())
                .email(fromAddUser.getEmail())
                .createdAt(new Date())
                .roles(roles)
                .status(true)
                .build();

        return userDao.add(user);
    }
}
