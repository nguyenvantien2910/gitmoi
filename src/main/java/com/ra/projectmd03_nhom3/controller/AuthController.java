package com.ra.projectmd03_nhom3.controller;

import com.ra.projectmd03_nhom3.constant.RoleName;
import com.ra.projectmd03_nhom3.dto.request.FormLogin;
import com.ra.projectmd03_nhom3.dto.request.FormRegister;
import com.ra.projectmd03_nhom3.model.User;
import com.ra.projectmd03_nhom3.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("formLogin", new FormLogin());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("formRegister", new FormRegister());
        return "register";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("formLogin") FormLogin formLogin, HttpSession session) {
        User user = userService.login(formLogin);
        if (user != null) {
            session.setAttribute("user", user);
        } else {
            return "redirect:/";
        }
        if (user.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN))) {
            return "redirect:/admin";
        }
        return "/admin/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin/home";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "_403";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("formRegister") FormRegister formRegister) {
        userService.register(formRegister);
        return "redirect:/";
    }
}
