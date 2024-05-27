package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.dao.IRoleDao;
import com.ra.projectmd03_nhom4.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements IRoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findByRoleName(RoleName roleName) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select r from roles r where r.roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
