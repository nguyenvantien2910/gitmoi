package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.ICommentDao;
import com.ra.projectmd03_nhom4.model.Comment;
import com.ra.projectmd03_nhom4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentDao commentDao;

    @Override
    public List<Comment> getCommentsByProductId(Long productId, Long userId) {
        return commentDao.getCommentsByProductId(productId, userId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.addComment(comment);
    }
}
