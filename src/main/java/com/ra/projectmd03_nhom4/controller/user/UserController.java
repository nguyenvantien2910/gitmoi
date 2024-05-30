package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    HttpSession session;

    @GetMapping("/homepage")
    public String homepage(Model model) {
        return "user/index";
    }

    @GetMapping("/shop")
    public String shop() {
        return "user/shop";
    }

    @GetMapping("/shop-detail")
    public String shopDetail() {
        return "user/shop-detail";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "user/order/checkout";
    }

    @GetMapping("/cart")
    public String cart() {
        return "user/cart";
    }

    @GetMapping("/info")
    public String userInfo(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        model.addAttribute("userLogin", userLogin);
        return "user/info";
    }
}
