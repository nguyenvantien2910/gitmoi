package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.constant.OrderStatus;
import com.ra.projectmd03_nhom4.model.Address;
import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import com.ra.projectmd03_nhom4.service.iplm.AddressServiceImpl;
import com.ra.projectmd03_nhom4.service.iplm.OrderService;
import com.ra.projectmd03_nhom4.service.iplm.ShoppingCartService;
import com.ra.projectmd03_nhom4.service.iplm.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping()
    public String orders(Model model) {
        model.addAttribute("orders",new Order());
        model.addAttribute("address",addressService.findByUserId(1L));
        double totalPrice = 0;
        for (ShoppingCart shoppingCart : cartService.findCartByUserId(1L)){
            totalPrice += shoppingCart.getProduct().getUnitPrice();
        }
        model.addAttribute("totalPrice",totalPrice);
        return "user/order/checkout";
    }
    @GetMapping("/checkout")
    public String checkout(Model model) {
        Order order = new Order();
        model.addAttribute("orders",order);
        return "user/order/checkout";
    }
    @PostMapping("/checkout")
    public String docheckout(@ModelAttribute() Order order) {
        order.setCreatedAt(new Date());
        order.setSerialNumber(UUID.randomUUID().toString());
        order.setUser(userService.findById(1L));
        order.setOrderStatus(OrderStatus.WAITING);
        orderService.addOrder(order);
        return "redirect:/order/order-success";
    }

    @GetMapping("/order-success")
    public String orderSuccess() {
        return "user/order/order-success";
    }
}
