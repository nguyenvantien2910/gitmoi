package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IProductDao;
import com.ra.projectmd03_nhom4.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAllProducts(int currentPage, int size) {
        Session session = sessionFactory.openSession();
        try {
            // HQL -> Hibernate Query Language
            return session.createQuery("from products ", Product.class)
                    .setFirstResult(currentPage * size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from products ", Product.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Product findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            // HQL -> Hibernate Query Language
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "from products p where p.productName LIKE :name";
            return session.createQuery(hql, Product.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding products by name", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            if (product.getProductId() == null) {
                // chức năng thêm mới
                session.save(product);
            } else {
                // chức năng update
                session.update(product);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


    @Override
    public String getImageByProductId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return (String) session.createQuery("select p.image from products p where p.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long countAllProduct() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(p.id) from products p").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> searchProduct(String name) {
        Session session = sessionFactory.openSession();
        try {
            List<Product> products = session.createQuery("FROM products p WHERE p.productName LIKE :name", Product.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}

