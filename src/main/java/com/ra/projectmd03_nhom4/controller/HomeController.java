package com.ra.projectmd03_nhom4.controller;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.model.*;
import com.ra.projectmd03_nhom4.service.IBannerService;
import com.ra.projectmd03_nhom4.service.IUserService;
import com.ra.projectmd03_nhom4.service.iplm.CategoryServiceIpml;
import com.ra.projectmd03_nhom4.service.iplm.ProductServiceImpl;
import com.ra.projectmd03_nhom4.service.iplm.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    HttpSession session;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CategoryServiceIpml categoryServiceIpml;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBannerService bannerService;

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
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin != null){
            model.addAttribute("userLogin", userLogin);
            List<ShoppingCart> cartList = shoppingCartService.findCartByUserId(userLogin.getUserId());
            session.setAttribute("cartList", cartList);
        } else {
            model.addAttribute("formLogin", new FormLogin());
        }
        List<Category> categoryList = categoryServiceIpml.findAll();
        session.setAttribute("categoryList", categoryList);

        List<Product> productList = productService.getAllProducts();
        session.setAttribute("productList", productList);

        List<Banner> bannerList = bannerService.findBannerToDisplay();
        session.setAttribute("bannerList", bannerList);

        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("formLogin") @Valid FormLogin formLogin, HttpSession session) {
        User user = userService.login(formLogin);
        if (user != null) {
            session.setAttribute("user", user);
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
    public String index(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin != null){
            model.addAttribute("userLogin", userLogin);
            List<ShoppingCart> cartList = shoppingCartService.findCartByUserId(userLogin.getUserId());
            session.setAttribute("cartList", cartList);
        }
        List<Category> categoryList = categoryServiceIpml.findAll();
        session.setAttribute("categoryList", categoryList);

        List<Product> productList = productService.getAllProducts();
        session.setAttribute("productList", productList);

        List<Banner> bannerList = bannerService.findBannerToDisplay();
        session.setAttribute("bannerList", bannerList);

        return "user/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userLogin");
        return "redirect:/login";
    }
}
