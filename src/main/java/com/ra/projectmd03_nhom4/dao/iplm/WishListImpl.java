package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IWishListDao;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListImpl implements IWishListDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAllWishList(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM products p WHERE p.id IN (SELECT w.product.id FROM wish_list w WHERE w.user.id = :userId)", Product.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }

    @Override
    public boolean addWishList(WishList wishList) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(wishList);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteWishList(Long userId, Long productId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM wish_list w WHERE user.id = :userId and product.id = :productId")
                    .setParameter("userId", userId).setParameter("productId", productId).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
