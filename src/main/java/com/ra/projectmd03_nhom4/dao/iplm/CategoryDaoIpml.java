package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.ICategoryDao;
import com.ra.projectmd03_nhom4.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryDaoIpml implements ICategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from categories").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            // HQL -> Hibernate Query Language
            return session.get(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }


    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            if (category.getCategoryId() == null) {
                // chức năng thêm mới
                session.save(category);
            } else {
                // chức năng update
                session.update(category);
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
    public void block(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Category category = findById(id);
            if (category != null) {
                category.setCategoryStatus(!category.getCategoryStatus()); // Đảo ngược giá trị status
                session.update(category);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public boolean checkCategoryName(String categoryName) {
        Session session = sessionFactory.openSession();
        try {
            Long count = (Long) session.createQuery("select count(*) from categories where lower(categoryName) = :name")
                    .setParameter("name", categoryName.toLowerCase())
                    .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


}

