package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.dto.request.CategoryDTO;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.service.ICategoryService;
import com.ra.projectmd03_nhom4.service.IProductServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/products")
public class UserProductController {

    @Autowired
    private IProductServiceUser productService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "user/shop-detail";
    }
//    @RequestMapping("/categories")
//    public String getProductCategory(Model model) {
//        List<CategoryDTO> categories = categoryService.findAllCategoriesWithProductCount();
//        model.addAttribute("categories", categories);
//        return "user/shop-detail";
//    }

    @RequestMapping("category/{id}")
    public String getCategoryDetails(@PathVariable Long id, Model model) {
        List<Product> products = productService.findByCategoryId(id);
        model.addAttribute("productCategory", products);
        return "user/index";
    }
}