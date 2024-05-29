package com.ra.projectmd03_nhom4.controller;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("formRegister", new FormRegister());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("formRegister") @Valid FormRegister formRegister) {
        userService.register(formRegister);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("formLogin", new FormLogin());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("formLogin") @Valid FormLogin formLogin, HttpSession session) {
        User user = userService.login(formLogin);
        if (user != null) {
            session.setAttribute("userLogin", user);
            if (user.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN))) {
                return "redirect:/admin";
            }
            return "redirect:/user/homepage";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/403")
    public String forbidden() {
        return "_403";
    }

    @RequestMapping("/")
    public String index() {
        return "user/index";
    }
}
