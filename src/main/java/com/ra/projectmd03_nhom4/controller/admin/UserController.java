package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.model.User;
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
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IService<User, Integer, String, Boolean, Long> userService;

    @GetMapping("/list")
    public String listUser(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(value = "sortField", defaultValue = "status") String sortField,
                           @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
                           @RequestParam(value = "searchQuery", defaultValue = "") String searchQuery,
                           @ModelAttribute("message") String message) {

        List<User> users = userService.findAll(page, size, sortField, sortDirection, searchQuery);
        long totalItems = userService.count(searchQuery);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("searchQuery", searchQuery);
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "admin/user/list";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/add";
    }

    @PostMapping("/insertUser")
    public String insertUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/user/add";
        } else {
            userService.add(user);
            redirectAttributes.addFlashAttribute("message", "User added successfully!");
            return "redirect:/list";
        }
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("userId") Integer userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/user/edit";
        } else {
            userService.update(user);
            redirectAttributes.addFlashAttribute("message", "Update user success!");
            return "redirect:/list";
        }
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Integer userId, RedirectAttributes redirectAttributes) {
        userService.delete(userId);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/list";
    }

    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("userId") Integer userId, @RequestParam("newStatus") Boolean newStatus, RedirectAttributes redirectAttributes) {
        userService.updateStatus(userId, newStatus);
        redirectAttributes.addFlashAttribute("message", "Update status success!");
        return "redirect:/list";
    }
}
