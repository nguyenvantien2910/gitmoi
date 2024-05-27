package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IUserDao;
import com.ra.projectmd03_nhom4.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogoutDaoImpl implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean register(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select u from users u where u.username = :username and u.password = :password", User.class)
                    .setParameter("username",username)
                    .setParameter("password",password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
