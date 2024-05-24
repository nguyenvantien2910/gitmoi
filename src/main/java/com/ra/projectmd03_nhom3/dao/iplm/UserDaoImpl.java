package com.ra.projectmd03_nhom3.dao.iplm;

import com.ra.projectmd03_nhom3.dao.IUserDao;
import com.ra.projectmd03_nhom3.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean register(Users users) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(users);
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
    public Users login(String username,String password) {
        Session session = sessionFactory.openSession();
        try {
            Users users = session.createQuery("select u from Users u where u.username = :username and u.password = :password",Users.class)
                    .setParameter("username",username)
                    .setParameter("password",password)
                    .getSingleResult();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
