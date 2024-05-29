package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.service.IProductServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/products")
public class UserProductController {

    @Autowired
    private IProductServiceUser productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "user/index";
    }
}