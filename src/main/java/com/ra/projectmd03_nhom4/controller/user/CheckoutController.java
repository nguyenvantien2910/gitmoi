package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.dto.request.UserCheckoutDTO;
import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.service.ICartService;
import com.ra.projectmd03_nhom4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    HttpSession session;

    @RequestMapping("/checkout")
    public String getCheckout(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/login";
        }
        UserCheckoutDTO userCheckoutDTO = new UserCheckoutDTO();
        userCheckoutDTO.setAddress(userLogin.getAddress());
        userCheckoutDTO.setPhone(userLogin.getPhone());
        userCheckoutDTO.setFullName(userLogin.getFullName());
        model.addAttribute("userCheckoutDTO", userCheckoutDTO);
        List<ShoppingCart> cartList = cartService.findCartByUserId(userLogin.getUserId());
        model.addAttribute("cartList", cartList);
        return "user/checkout";
    }

    @PostMapping("/checkout")
    public String handleCheckout(@ModelAttribute("userCheckoutDTO") UserCheckoutDTO userCheckoutDTO) {
        List<ShoppingCart> cartList = (List<ShoppingCart>) session.getAttribute("cartList");
        User userLogin = (User) session.getAttribute("userLogin");
        boolean checkQty = true;
        for (ShoppingCart cart : cartList) {
            if (cart.getOrderQuantity() > cart.getProduct().getStockQuantity()){
                checkQty = false;
            }
        }
        if (checkQty) {
            Order newOrder = new Order();
            newOrder.setUser(userLogin);
            newOrder.setNote(userCheckoutDTO.getNote());
            newOrder.setReceiveName(userCheckoutDTO.getFullName());
            newOrder.setReceiveAddress(userCheckoutDTO.getAddress());
            newOrder.setReceivePhone(userCheckoutDTO.getPhone());
            newOrder.setTotalPrice((Double) session.getAttribute("totalPrice"));
            orderService.orderCheckout(newOrder);
            cartService.removeCartByUserId(userLogin.getUserId());
            return "redirect:/user/order/order-success";
        }else {
            return "redirect:/shoppingcart";
        }
    }
}
