package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.constant.OrderStatus;
import com.ra.projectmd03_nhom4.dao.IOrderDAO;
import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.model.OrderDetail;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private OrderDetailDaoImpl orderDetailDao;

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
//            Order newOrder = getOrderById(saveOrder(order));
            session.save(order);
            session.getTransaction().commit();

            List<ShoppingCart> cartList = cartDao.findCartByUserId(order.getUser().getUserId());
            for (ShoppingCart cart : cartList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(cart.getProduct());
                orderDetail.setUnitPrice(cart.getProduct().getUnitPrice());
                orderDetail.setOrderQuantity(cart.getOrderQuantity());
                orderDetailDao.save(orderDetail);
            }
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

    @Override
    public Long saveOrder(Order order) {
        Session session = sessionFactory.openSession();
        Long orderId = 0L;
        try {
            StoredProcedureQuery query = session.createStoredProcedureQuery("PROC_SAVE_ORDER");

            query.registerStoredProcedureParameter("o_userid", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("o_order_name", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("o_address", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("o_phone", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("o_note", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("o_total", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("o_order_id", Long.class, ParameterMode.OUT);

            query.setParameter("o_userid", order.getUser().getUserId());
            query.setParameter("o_order_name", order.getReceiveName());
            query.setParameter("o_address", order.getReceiveAddress());
            query.setParameter("o_phone", order.getReceivePhone());
            query.setParameter("o_note", order.getNote());
            query.setParameter("o_total", order.getTotalPrice());
            query.setParameter("o_order_id", orderId);

            query.execute();

            return (Long) query.getOutputParameterValue("order_id");
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return orderId;
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            Order order = session.get(Order.class, orderId);
            if (order != null) {
                order.setOrderStatus(OrderStatus.CANCEL);
                session.update(order);
            }
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean acceptOrder(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            if (order != null) {
                order.setOrderStatus(OrderStatus.SUCCESS);
                session.update(order);
            }
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}
