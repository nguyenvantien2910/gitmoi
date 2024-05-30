package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.model.Category;
import com.ra.projectmd03_nhom4.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller

@RequestMapping(value = "/admin/category")


public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public String categoryManagement(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/category/list-category";
    }

    @GetMapping("/add-category")
    public String openAddModal(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add-category";
    }
    @PostMapping("/add-category")
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            return "admin/category/add-category";
        }
        if(categoryService.checkCategoryName(category.getCategoryName())){
            return "redirect:admin/category/add-category";
        }
        categoryService.saveOrUpdate(category);
        redirectAttributes.addFlashAttribute("mess", "Thêm mới danh mục thành công !");

        return "redirect:/admin/category";

    }


    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category/edit-category";
    }

    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/category/edit-category";
        }
        categoryService.saveOrUpdate(category);

        return "redirect:/admin/category";

    }

    @GetMapping("/{id}")
    public String blockCategory(@PathVariable("id") Long id) {
        categoryService.block(id);

        return "redirect:/admin/category/list";

    }

}
