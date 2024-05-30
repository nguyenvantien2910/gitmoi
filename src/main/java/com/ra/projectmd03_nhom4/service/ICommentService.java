package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getCommentsByProductId(Long productId, Long userId);
    void saveComment(Comment comment);
}
