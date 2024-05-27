package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IDAO;
import com.ra.projectmd03_nhom4.model.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoIplm implements IDAO<Category, Integer, String, Boolean, Long> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder("FROM categories c");

            // Lọc tìm kiếm theo tên nếu có
            if (searchQuery != null && !searchQuery.isEmpty()) {
                hql.append(" WHERE c.categoryName LIKE :searchQuery");
            }

            // Set Sort theo chỉ định tương ứng
            if (sortField != null && !sortField.isEmpty()) {
                hql.append(" ORDER BY c.").append(sortField).append(" ").append(sortDirection);
            } else {
                hql.append(" ORDER BY c.categoryId ASC, c.categoryStatus ASC");
            }

            Query<Category> query = session.createQuery(hql.toString(), Category.class);

            // set tìm kếm theo tên nếu có
            if (searchQuery != null && !searchQuery.isEmpty()) {
                query.setParameter("searchQuery", "%" + searchQuery + "%");
            }

            query.setFirstResult(pageNo * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            session.close();
        }
    }


    @Override
    public Long count(String searchQuery) {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(c.categoryName) from categories c").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Category findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean add(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateStatus(Integer id, Boolean newStatus) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "update categories set categoryStatus = :newStatus where categoryId = :categoryId";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("newStatus", newStatus)
                    .setParameter("categoryId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return updatedEntities > 0;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
