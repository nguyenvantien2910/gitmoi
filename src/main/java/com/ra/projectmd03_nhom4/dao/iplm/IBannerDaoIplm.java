package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.IBannerDao;
import com.ra.projectmd03_nhom4.model.Banner;
import com.ra.projectmd03_nhom4.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IBannerDaoIplm implements IBannerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Banner> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder("FROM banners b");

            if (searchQuery != null && !searchQuery.isEmpty()) {
                hql.append(" WHERE b.title LIKE :searchQuery");
            }

            if (sortField != null && !sortField.isEmpty()) {
                hql.append(" ORDER BY b.").append(sortField).append(" ").append(sortDirection);
            } else {
                hql.append(" ORDER BY b.id ASC");
            }

            Query<Banner> query = session.createQuery(hql.toString(), Banner.class);

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

    @Override
    public List<Banner> findBannerToDisplay() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from banners where isDisplay = true order by id desc ", Banner.class)
                    .setMaxResults(5)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding banners to display", e);
        } finally {
            session.close();
        }
    }


    @Override
    public Banner findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            // HQL -> Hibernate Query Language
            return session.get(Banner.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Banner banner) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            if (banner.getId() == null) {
                // chức năng thêm mới
                session.save(banner);
            } else {
                // chức năng update
                session.update(banner);
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
    public String getImageByBannerId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return (String) session.createQuery("select b.url from banners b where b.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Long countAllBanner() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(b.id) from banners b").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateStatus(Long id, Boolean newStatus) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "UPDATE banners SET isDisplay = :newStatus WHERE id = :id";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("newStatus", newStatus)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return updatedEntities > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
