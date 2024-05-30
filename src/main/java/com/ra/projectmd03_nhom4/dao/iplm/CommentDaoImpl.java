package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.ICommentDao;
import com.ra.projectmd03_nhom4.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDaoImpl implements ICommentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Comment> getCommentsByProductId(Long productId, Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Comment where product.id = :productId and user.id = :userId", Comment.class)
                    .setParameter("productId", productId)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(comment);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Comment comment = session.get(Comment.class, commentId);
            if (comment != null) {
                session.delete(comment);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
