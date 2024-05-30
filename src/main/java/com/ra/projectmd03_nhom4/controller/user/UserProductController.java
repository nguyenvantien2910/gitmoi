package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.dto.request.CategoryDTO;
import com.ra.projectmd03_nhom4.model.Comment;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.service.ICategoryService;
import com.ra.projectmd03_nhom4.service.ICommentService;
import com.ra.projectmd03_nhom4.service.IProductService;
import com.ra.projectmd03_nhom4.service.IProductServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/products")
public class UserProductController {

    @Autowired
    private IProductServiceUser productService;
    @Autowired
    private IProductService productServiceAdmin;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICommentService commentService;

    @RequestMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Long userId = 1L;
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("comments", commentService.getCommentsByProductId(id, userId));
//        model.addAttribute("newComment", new Comment());
        return "user/shop-detail";
    }

    @RequestMapping("category/{id}")
    public String getCategoryDetails(@PathVariable Long id, Model model) {
        List<Product> products = productService.findByCategoryId(id);
        model.addAttribute("productCategory", products);
        return "user/index";
    }

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("newComment") Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("name") String name, Model model) {
        List<Product> products = productServiceAdmin.searchProduct(name);
        model.addAttribute("products", products);
        return "user/index";
    }

}