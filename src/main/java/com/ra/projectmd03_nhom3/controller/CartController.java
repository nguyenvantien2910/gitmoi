package com.ra.projectmd03_nhom3.controller;

import com.ra.projectmd03_nhom3.model.Product;
import com.ra.projectmd03_nhom3.model.User;
import com.ra.projectmd03_nhom3.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    @Autowired
    HttpSession session;
    @Autowired
    private ICartService cartService;
//    @Autowired
//    private IProductService productService;

    @GetMapping("/shoppingcart")
    public String shoppingCart() {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/login";
        }else {
            cartService.findCartByUserId(Math.toIntExact(userLogin.getUserId()));
            return "user/shoppingcart";
        }
    }

    @GetMapping("/add-one/{id}")
    public String addOne(@PathVariable("product_id") int product_id) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/login";
        }else {
            if (cartService.checkExistProductInCart(userLogin.getUserId(), product_id)) {
//                Product product = productService.findById(product_id);
//                cartService.updateCart(product, Math.toIntExact(userLogin.getUserId()),1);
            }else {
//                Product product = productService.findById(product_id);
//                cartService.addToCart(product, Math.toIntExact(userLogin.getUserId()),1);
            }
            return "redirect:/shoppingcart";
        }
    }

    @PostMapping("add-to-cart")
    public String addToCart(@RequestParam("product_id") int product_id,@RequestParam("quantity") int quantity, Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/login";
        }else {
            if (cartService.checkExistProductInCart(userLogin.getUserId(), product_id)) {
//                Product product = productService.findById(product_id);
//                cartService.updateCart(product, Math.toIntExact(userLogin.getUserId()),quantity);
            }else {
//                Product product =  productService.findById(product_id);
//                cartService.addToCart(product, Math.toIntExact(userLogin.getUserId()),quantity);
            }
            return "redirect:/shoppingcart";
        }
    }

    @GetMapping("/remove-item/{id}")
    public String removeItem(@PathVariable("id") int id) {
        cartService.removeFromCart(id);
        return "redirect:/shoppingcart";
    }

    @PostMapping("update-cart-by-id")
    public String updateCartById(@RequestParam("cartId") int cartId, @RequestParam("quantity") int quantity) {
        if (quantity > 0) {
            cartService.updateCartById(cartId, quantity);
        }else {
            cartService.removeFromCart(cartId);
        }
        return "redirect:/shoppingcart";
    }
}
