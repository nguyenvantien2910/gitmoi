package com.ra.projectmd03_nhom4.dao.iplm;

import com.ra.projectmd03_nhom4.dao.ICommentDao;
import com.ra.projectmd03_nhom4.model.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements ICommentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Comment> getCommentsByProductId(Long productId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Comment where product.id = :productId", Comment.class)
                .setParameter("productId", productId)
                .list();

    }

    @Override
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().saveOrUpdate(comment);
    }



}
