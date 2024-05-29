package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IProductDaoUser;
import com.ra.projectmd03_nhom4.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductDaoUserImpl implements IProductDaoUser {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("from products ", Product.class).list();
        session.close();
        return products;
    }

    @Override
    public Product findById(Long id) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public void saveOrUpdate(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.delete(product);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return sessionFactory.openSession()
                .createQuery("FROM products WHERE category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

}
