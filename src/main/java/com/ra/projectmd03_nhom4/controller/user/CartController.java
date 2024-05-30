package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.ICartService;
import com.ra.projectmd03_nhom4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    HttpSession session;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;

    // 1959- hiển thị danh sách cart item
    @GetMapping("/shoppingcart")
    public String shoppingCart(Model model) {
//        User userLogin = (User) session.getAttribute("userLogin");
//        if (userLogin == null) {
//            return "redirect:/login";
//        }else {
            List<ShoppingCart> list = cartService.findCartByUserId(1L);    //(Math.toIntExact(userLogin.getUserId()));
            model.addAttribute("cartList",list);
            return "user/shoppingcart";
//        }
    }

    @GetMapping("/add-one/{product_id}")
    public String addOne(@PathVariable("product_id") long product_id) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/login";
        }else {
            if (cartService.checkExistProductInCart(userLogin.getUserId(), product_id)) {
                Product product = productService.findById(product_id);
                cartService.updateCart(product, userLogin.getUserId(),1);
            }else {
                Product product = productService.findById(product_id);
                cartService.addToCart(product, userLogin.getUserId(),1);
            }
            return "redirect:/shoppingcart";
        }
    }

    @PostMapping("/add-to-cart/{product_id}")
    public String addToCart(@RequestParam("product_id") long product_id,@RequestParam("quantity") int quantity) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/login";
        }else {
            if (cartService.checkExistProductInCart(userLogin.getUserId(), product_id)) {
                Product product = productService.findById(product_id);
                cartService.updateCart(product, userLogin.getUserId(),quantity);
            }else {
                Product product =  productService.findById(product_id);
                cartService.addToCart(product, userLogin.getUserId(),quantity);
            }
            return "redirect:/shoppingcart";
        }
    }

    //1961 - xóa 1 sản phẩm
    @GetMapping("/delete-item/{cartItemId}")
    public String removeItem(@PathVariable("cartItemId") Long id) {
        cartService.removeFromCart(id);
        return "redirect:/shoppingcart";
    }

    //1961- xóa hết sản phẩm
    @GetMapping("/delete-all-item/{userId}")
    public String removeAllItems(@PathVariable("userId") Long userId) {
        cartService.removeCartByUserId(userId);
        return "redirect:/shoppingcart";
    }

    // 1960 thay đổi thông số mua hàng - plus
    @GetMapping("/plus/{shoppingCartId}")
    public String plus(@PathVariable("shoppingCartId") Long shoppingCartId, Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        model.addAttribute("cartList",cartService.findCartByUserId(1L));
        cartService.updateCartById(shoppingCartId,cartService.findCartById(shoppingCartId).getOrderQuantity()+1);
        return "redirect:/shoppingcart";
    }

    // 1960 thay đổi thông số mua hàng - minus
    @GetMapping("/minus/{shoppingCartId}")
    public String minus(@PathVariable("shoppingCartId") Long shoppingCartId, Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        model.addAttribute("cartList",cartService.findCartByUserId(1L));
        cartService.updateCartById(shoppingCartId,cartService.findCartById(shoppingCartId).getOrderQuantity()-1);
        return "redirect:/shoppingcart";
    }
}
