package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IOrderDAO;
import com.ra.projectmd03_nhom4.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.openSession();
        try {
            List<Order> orders = session.createQuery("from orders ").list();
            return orders;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Order.class, id);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        try {
            List<Order> orderList = session.createQuery("select o from orders o where o.user.id = :userId").setParameter("userId", userId).list();
            return orderList;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addOrder(Order order) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }
}
