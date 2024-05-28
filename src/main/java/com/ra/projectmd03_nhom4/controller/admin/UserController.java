package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.dto.request.FromAddUser;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.IUserService;
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
    private IUserService userService;

    @GetMapping("/list")
    public String listUser(Model model,
                           @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                           @RequestParam(value = "size", defaultValue = "5", required = false) int size,
                           @RequestParam(value = "sortField", defaultValue = "status", required = false) String sortField,
                           @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection,
                           @RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery,
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
        model.addAttribute("user", new FromAddUser());
        return "admin/user/add";
    }

    @PostMapping("/insertUser")
    public String insertUser(@ModelAttribute("user") @Valid FromAddUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/user/add";
        } else {
            userService.addNewAdmin(user);
            redirectAttributes.addFlashAttribute("message", "User added successfully!");
            return "redirect:/admin/user/list";
        }
    }

    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user")  User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/user/edit";
        } else {
            userService.update(user);
            redirectAttributes.addFlashAttribute("message", "Update user success!");
            return "redirect:/admin/user/list";
        }
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, RedirectAttributes redirectAttributes) {
        userService.delete(userId);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/admin/user/list";
    }

    @GetMapping("/updateStatus/{userId}")
    public String updateStatus(@PathVariable("userId") Long userId, @RequestParam("newStatus") Boolean newStatus, RedirectAttributes redirectAttributes) {
        userService.updateStatus(userId, newStatus);
        redirectAttributes.addFlashAttribute("message", "Update status success!");
        return "redirect:/admin/user/list";
    }
}
