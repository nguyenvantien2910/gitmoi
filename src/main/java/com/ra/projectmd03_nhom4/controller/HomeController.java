package com.ra.projectmd03_nhom4.controller;

import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.dto.request.FormLogin;
import com.ra.projectmd03_nhom4.dto.request.FormRegister;
import com.ra.projectmd03_nhom4.model.Category;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.*;
import com.ra.projectmd03_nhom4.model.*;
import com.ra.projectmd03_nhom4.service.iplm.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("formRegister", new FormRegister());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("formRegister") @Valid FormRegister formRegister , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userService.register(formRegister);
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin != null) {
            model.addAttribute("userLogin", userLogin);
            List<ShoppingCart> cartList = shoppingCartService.findCartByUserId(userLogin.getUserId());
            session.setAttribute("cartList", cartList);
        } else {
            model.addAttribute("formLogin", new FormLogin());
        }
        List<Category> categoryList = categoryService.findAll();
        session.setAttribute("categoryList", categoryList);

        List<Product> productList = productService.getAllProducts();
        session.setAttribute("productList", productList);

        List<Banner> bannerList = bannerService.findBannerToDisplay();
        session.setAttribute("bannerList", bannerList);


        model.addAttribute("formLogin", new FormLogin());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("formLogin") @Valid FormLogin formLogin, HttpSession session) {
        User userLogin = userService.login(formLogin);
        if (userLogin != null) {
            session.setAttribute("userLogin", userLogin);
            if (userLogin.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN))) {
                return "redirect:/admin";
            } else {
                List<ShoppingCart> cartList = shoppingCartService.findCartByUserId(userLogin.getUserId());
                session.setAttribute("cartList", cartList);
                return "redirect:/user/homepage";
            }
        }
        return "/login";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "_403";
    }

    @RequestMapping("/")
    public String index(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin != null) {
            session.setAttribute("userLogin", userLogin);
            List<ShoppingCart> cartList = shoppingCartService.findCartByUserId(userLogin.getUserId());
            session.setAttribute("cartList", cartList);
        }
        List<Product> products = productService.getAllProducts();
        session.setAttribute("products", products);

        List<Category> categoryList = categoryService.findAll();
        session.setAttribute("categoryList", categoryList);

        List<Banner> bannerList = bannerService.findBannerToDisplay();
        model.addAttribute("bannerList", bannerList);
        session.setAttribute("activePage", "index");

        return "user/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userLogin");
        return "redirect:/login";
    }
}
