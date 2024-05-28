package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IAddressDao;
import com.ra.projectmd03_nhom4.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDaoImpl implements IAddressDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Address> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List<Address> addressList = session.createQuery("from addresses ").list();
            return addressList;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Address findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Address.class, id);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Address> findByUserId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            List<Address> addressList =(List<Address>) session.createQuery("select a from addresses a where a.user.userId = :userId").list();
            return addressList;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(Address address) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(address);
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
    public boolean update(Address address) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(address);
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
    public boolean delete(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
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
