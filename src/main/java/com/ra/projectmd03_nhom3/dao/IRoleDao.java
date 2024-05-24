package com.ra.projectmd03_nhom3.dao;

import com.ra.projectmd03_nhom3.constant.RoleName;
import com.ra.projectmd03_nhom3.model.Roles;

public interface IRoleDao {
    Roles findByRoleName(RoleName roleName);
}
