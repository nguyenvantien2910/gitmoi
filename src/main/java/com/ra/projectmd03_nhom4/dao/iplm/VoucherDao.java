package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IVoucherDao;
import com.ra.projectmd03_nhom4.model.Voucher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherDao implements IVoucherDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long findAllCode(String code) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder("SELECT COUNT(v) FROM Voucher v");
            if (code != null && !code.isEmpty()) {
                hql.append(" WHERE v.voucherCode LIKE :code");
            }
            Query<Long> query = session.createQuery(hql.toString(), Long.class);

            if (code != null && !code.isEmpty()) {
                query.setParameter("code", "%" + code + "%");
            }

            return query.uniqueResult();
        } finally {
            session.close();
        }
    }


    @Override
    public List<Voucher> findAllCode() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Voucher> list = session.createQuery("from Voucher").list();
            session.getTransaction().commit();
            return list;
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override

    public Voucher findByCode(Long codeId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(Voucher.class, codeId);
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


    public boolean checkVoucherCode(String voucherCode) {
        Session session = sessionFactory.openSession();
        try {
            List<Voucher> voucherList = session.createQuery("select v from Voucher v where v.voucherCode = :voucherCode")
                    .setParameter("voucherCode", voucherCode).list();
            return voucherList != null && !voucherList.isEmpty();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Voucher> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder("FROM Voucher v");

            if (searchQuery != null && !searchQuery.isEmpty()) {
                hql.append(" WHERE v.voucherCode LIKE :searchQuery");
            }

            if (sortField != null && !sortField.isEmpty()) {
                hql.append(" ORDER BY v.").append(sortField).append(" ").append(sortDirection);
            } else {
                hql.append(" ORDER BY v.voucherId DESC");
            }

            Query<Voucher> query = session.createQuery(hql.toString(), Voucher.class);

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
}
