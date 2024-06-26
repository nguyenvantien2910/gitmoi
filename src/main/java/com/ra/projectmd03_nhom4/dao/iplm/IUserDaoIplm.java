package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IUserDao;
import com.ra.projectmd03_nhom4.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IUserDaoIplm implements IUserDao<User, Integer, String, Boolean, Long> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder("FROM users u");

            if (searchQuery != null && !searchQuery.isEmpty()) {
                hql.append(" WHERE u.fullName LIKE :searchQuery");
            }

            if (sortField != null && !sortField.isEmpty()) {
                hql.append(" ORDER BY u.").append(sortField).append(" ").append(sortDirection);
            } else {
                hql.append(" ORDER BY u.userId ASC");
            }

            Query<User> query = session.createQuery(hql.toString(), User.class);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                query.setParameter("searchQuery", "%" + searchQuery + "%");
            }

            query.setFirstResult(pageNo * pageSize);
            query.setMaxResults(pageSize);

            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Long count(String searchQuery) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder("SELECT COUNT(u) FROM users u");
            if (searchQuery != null && !searchQuery.isEmpty()) {
                hql.append(" WHERE u.fullName LIKE :searchQuery");
            }
            Query<Long> query = session.createQuery(hql.toString(), Long.class);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                query.setParameter("searchQuery", "%" + searchQuery + "%");
            }

            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    @Override
    public User findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(User.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean add(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateStatus(Long id, Boolean newStatus) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "UPDATE users SET status = :newStatus WHERE userId = :userId";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("newStatus", newStatus)
                    .setParameter("userId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return updatedEntities > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                session.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

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
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNewAdmin(User user) {
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
    public boolean findUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "from users u where u.username = :name";
            User user = session.createQuery(hql, User.class)
                    .setParameter("name", username)
                    .uniqueResult();
            if (user != null) {
                return true;
            };
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by username", e);
        } finally {
            session.close();
        }
    }

    @Override
    public String getAvaterByUserId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return (String) session.createQuery("select u.avatar from users u where u.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}
