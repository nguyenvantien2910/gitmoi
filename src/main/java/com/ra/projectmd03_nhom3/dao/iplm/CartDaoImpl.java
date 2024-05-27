package com.ra.projectmd03_nhom3.dao.iplm;

import com.ra.projectmd03_nhom3.dao.ICartDao;
import com.ra.projectmd03_nhom3.dao.IUserDao;
import com.ra.projectmd03_nhom3.model.Product;
import com.ra.projectmd03_nhom3.model.ShoppingCart;
import com.ra.projectmd03_nhom3.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Repository
public class CartDaoImpl implements ICartDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public List<ShoppingCart> findCartByUserId(Integer userId) {
        Transaction transaction = null;
        List<ShoppingCart> cartList = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<ShoppingCart> query = session.createQuery("from shopping_cart where user.userId = :userId");
            query.setParameter("userId", userId);
            cartList = query.getResultList();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
        return cartList;
    }

    @Override
    public boolean addToCart(Product product, Integer user_id, Integer quantity) {
        Transaction transaction = null;
        try {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
            User userLogin = session.get(User.class, user_id);
            if (userLogin == null) {
                throw new RuntimeException("user not found");
            }
            ShoppingCart cart = new ShoppingCart();
            cart.setUser(userLogin);
            cart.setProduct(product);
            cart.setOrderQuantity(quantity);

            session.save(cart);

            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
        return false;
    }

    @Override
    public void removeFromCart(Integer cart_id) {
        Transaction transaction = null;
        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            ShoppingCart cart = session.get(ShoppingCart.class, cart_id);
            if (cart != null){
                session.delete(cart);
            }
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }

    }

    @Override
    public void removeCartByUserId(Integer user_id) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from shopping_cart where user.userId = :userId");
            query.setParameter("userId", user_id);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }

    }

    @Override
    public void updateCartById(Integer cart_id, Integer quantity) {
        Transaction transaction = null;
        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            ShoppingCart cart = session.get(ShoppingCart.class, cart_id);
            if (cart != null){
                cart.setOrderQuantity(quantity);
                session.update(cart);
            }

            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
    }

    @Override
    public boolean updateCart(Product product, Integer user_id, Integer quantity) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<ShoppingCart> query = session.createQuery("from shopping_cart where user.userId = :userId and product.productId = :productId");
            query.setParameter("userId", user_id);
            query.setParameter("productId", product.getProductId());
            ShoppingCart cart = query.getSingleResult();
            if (cart != null){
                cart.setOrderQuantity(quantity);
                session.update(cart);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            sessionFactory.close();
        }
    }
}
