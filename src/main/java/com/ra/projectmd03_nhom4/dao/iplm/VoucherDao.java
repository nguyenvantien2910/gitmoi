package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IVoucherDao;
import com.ra.projectmd03_nhom4.model.Voucher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class VoucherDao implements IVoucherDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Voucher> findAllCode() {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<Voucher> list = session.createQuery("from Voucher").list();
            session.getTransaction().commit();
            return list;
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Voucher findByCode(String code) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(Voucher.class, code);
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean save(Voucher voucher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(voucher);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Voucher voucher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(voucher);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Voucher voucher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(voucher);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}
