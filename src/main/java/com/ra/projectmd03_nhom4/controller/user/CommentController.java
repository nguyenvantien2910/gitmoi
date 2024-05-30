package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.model.Comment;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.ICommentService;
import com.ra.projectmd03_nhom4.service.IProductService;
import com.ra.projectmd03_nhom4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("/user")
public class CommentController {
//    @Autowired
//    private ICommentService commentService;
//
//    @Autowired
//    private IProductService productService;
//
//    @Autowired
//    private IUserService userService;
//
//    @GetMapping("/product/{productId}/comments")
//    public String viewComments(@PathVariable("productId") Long productId, Model model) {
//        List<Comment> comments = commentService.getCommentsByProductId(productId);
//        model.addAttribute("comments", comments);
//        return "user/comments";
//    }
//
//    @PostMapping("/product/{productId}/comments/add")
//    public String addComment(@PathVariable("productId") Long productId, @RequestParam("content") String content) {
//        Long userId = 1L; // Retrieve the currently logged-in user's ID appropriately
//        User user = userService.findById(userId);
//        Product product = productService.findById(productId);
//
//        Comment comment = new Comment();
//        comment.setComment(content);
//        comment.setCreatedDate(new Date());
//        comment.setUser(user);
//        comment.setProduct(product);
//
//        commentService.addComment(comment);
//
//        return "redirect:/user/product/" + productId + "/comments";
//    }
//
//    @GetMapping("/comments/delete/{commentId}")
//    public String deleteComment(@PathVariable("commentId") Long commentId) {
//        commentService.deleteComment(commentId);
//        return "redirect:/user/product/{productId}/comments"; // Adjust as necessary
//    }
}
