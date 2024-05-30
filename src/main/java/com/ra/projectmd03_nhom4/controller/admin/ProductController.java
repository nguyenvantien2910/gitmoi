package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.dto.request.ProductRequest;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.service.ICategoryService;
import com.ra.projectmd03_nhom4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String homeProduct(Model model, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "2") int size) {
        List<Product> products = productService.findAll(currentPage, size);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countProduct() / size));
        model.addAttribute("products", products);
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/list-product";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("name") String name, Model model) {
        List<Product> products = productService.searchProduct(name);
        model.addAttribute("products", products);
        return "admin/product/list-product";
    }


    @GetMapping("/initAddProduct")
    public String initAddProduct(Model model) {
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/add-product";
    }

    @PostMapping("/handleAddProduct")
    public String handleAddProduct(@Validated @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", new ProductRequest());
            model.addAttribute("categories", categoryService.findAll());
            return "admin/product/add-product";
        }
        productService.saveOrUpdate(productRequest);
        return "redirect:/product";
    }

    // /product/{id}/edit
    @GetMapping("/{id}/edit")
    public String viewEditProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/edit-product";
    }

    @PostMapping("/handleEditProduct/{id}")
    public String handleEditProduct(@ModelAttribute("product") ProductRequest productRequest, @PathVariable Long id) {
        productRequest.setProductId(id);
        productService.saveOrUpdate(productRequest);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }
}