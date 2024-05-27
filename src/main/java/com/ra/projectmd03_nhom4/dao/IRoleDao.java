package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.model.Role;

public interface IRoleDao {
    Role findByRoleName(RoleName roleName);
}
