package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IOrderDAO;
import com.ra.projectmd03_nhom4.dao.IOrderDetailDAO;
import com.ra.projectmd03_nhom4.dao.IProductDao;
import com.ra.projectmd03_nhom4.model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.util.List;
@Repository
public class OrderDetailDaoImpl implements IOrderDetailDAO {
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IProductDao productDAO;
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(OrderDetail orderDetail) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(orderDetail);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<OrderDetail> getOrderDetail(Long orderId) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from order_details", OrderDetail.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
