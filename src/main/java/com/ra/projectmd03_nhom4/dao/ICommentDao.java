package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Comment;

import java.util.List;

public interface ICommentDao {
    List<Comment> getCommentsByProductId(Long productId);
    boolean addComment(Comment comment);
    void deleteComment(Long commentId);
}
