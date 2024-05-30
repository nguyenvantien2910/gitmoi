package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IOrderDAO;
import com.ra.projectmd03_nhom4.dao.IOrderDetailDAO;
import com.ra.projectmd03_nhom4.dao.IProductDao;
import com.ra.projectmd03_nhom4.model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<OrderDetail> getOrderDetail(Long orderId) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from order_details", OrderDetail.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Integer countSales() {
        Session session = sessionFactory.openSession();
        List<OrderDetail> orderDetails = session.createQuery("from order_details", OrderDetail.class).getResultList();
        Integer countSales = 0;
        for (OrderDetail orderDetail : orderDetails) {
            countSales += orderDetail.getOrderQuantity();
        }
        return countSales;
    }

    @Override
    public Integer sumTotal() {
        Session session = sessionFactory.openSession();
        List<OrderDetail> orderDetails = session.createQuery("from order_details", OrderDetail.class).getResultList();
        double sumTotal = 0;
        for (OrderDetail orderDetail : orderDetails) {
            sumTotal += orderDetail.getOrderQuantity()*orderDetail.getProduct().getUnitPrice();
        }
        return (int)sumTotal;
    }

    @Override
    public Integer countOrder(){
        Session session = sessionFactory.openSession();
        List<OrderDetail> orderDetails = session.createQuery("from order_details", OrderDetail.class).getResultList();
        Integer countOrder = 0;
        for (OrderDetail orderDetail : orderDetails) {
            countOrder += 1;
        }
        return countOrder;
    }

    @Override
    public Integer countVisitor(){
        Session session = sessionFactory.openSession();
        List<OrderDetail> orderDetails = session.createQuery("from order_details", OrderDetail.class).getResultList();
        int countVisitor = 1;
        Long visitorId = orderDetails.get(0).getOrder().getUser().getUserId();
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getOrder().getUser().getUserId() != visitorId) {
                countVisitor += 1;
                visitorId = orderDetail.getOrder().getUser().getUserId();
            }
        }
        return countVisitor;
    }
}
