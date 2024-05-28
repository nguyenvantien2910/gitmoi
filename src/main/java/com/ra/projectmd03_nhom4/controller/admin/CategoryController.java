package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.model.Category;
import com.ra.projectmd03_nhom4.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class CategoryController {
    @Autowired
    private IService<Category, Integer, String, Boolean, Long> categoryService;

    @GetMapping({""})
    public String listCategory(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @RequestParam(value = "sortField", defaultValue = "status") String sortField,
                               @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
                               @RequestParam(value = "searchQuery", defaultValue = "") String searchQuery,
                               @ModelAttribute("message") String message) {

        List<Category> categories = categoryService.findAll(page, size, sortField, sortDirection, searchQuery);
        long totalItems = categoryService.count(searchQuery);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("message", message);

        return "admin/category/list";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add";
    }

    @PostMapping("/insertCategory")
    public String insertCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/category/add";
        } else {
            categoryService.add(category);
            redirectAttributes.addFlashAttribute("message", "Category added successfully!");
            return "redirect:/category/list";
        }
    }

    @GetMapping("/edit")
    public String editCategory(@RequestParam("cateId") Integer cateId, Model model) {
        Category category = categoryService.findById(cateId);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/category/edit";
        } else {
            categoryService.update(category);
            redirectAttributes.addFlashAttribute("message", "Update category success!");
            return "redirect:/category/list";
        }
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("cateId") Integer cateId, RedirectAttributes redirectAttributes) {
        categoryService.delete(cateId);
        redirectAttributes.addFlashAttribute("message", "Category deleted successfully!");
        return "redirect:/category/list";
    }

    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("cateId") Integer cateId, @RequestParam("newStatus") Boolean newStatus, RedirectAttributes redirectAttributes) {
        categoryService.updateStatus(cateId, newStatus);
        redirectAttributes.addFlashAttribute("message", "Update status success!");
        return "redirect:/category/list";
    }
}
