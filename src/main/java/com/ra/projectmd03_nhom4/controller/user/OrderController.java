package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.constant.OrderStatus;
import com.ra.projectmd03_nhom4.dao.iplm.CartDaoImpl;
import com.ra.projectmd03_nhom4.dao.iplm.VoucherDao;
import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.model.Voucher;
import com.ra.projectmd03_nhom4.service.iplm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServiceIplm userService;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private HttpSession session;
    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/clients")
    public String ordersClients(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        model.addAttribute("orderList",orderService.getOrdersByUserId(userLogin.getUserId()));
        double totalPrice = 0;
        for(ShoppingCart shoppingCart : cartService.findCartByUserId(userLogin.getUserId())){
            totalPrice += shoppingCart.getProduct().getUnitPrice();
            model.addAttribute("totalPrice",totalPrice);
        }
        return "user/order/clientOrders";
    }

    @GetMapping("/viewDetail/{orderId}")
    public String orderDetail(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("orders",orderService.getOrderById(orderId));
        return "user/order/orderViewDetail";
    }

    @GetMapping("clients/update/{orderId}")
    public String orderUpdate(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("orders",orderService.getOrderById(orderId));
        return "orders/updateOrder";
    }

    @GetMapping()
    public String orders(Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        model.addAttribute("orders", new Order());
        List<ShoppingCart> cartList = cartService.findCartByUserId(userLogin.getUserId());
        model.addAttribute("cartList", cartList);
        double totalPrice = 0;
        for(ShoppingCart shoppingCart : cartService.findCartByUserId(userLogin.getUserId())){
            totalPrice += shoppingCart.getProduct().getUnitPrice()*shoppingCart.getOrderQuantity();
        }
        model.addAttribute("totalPrice",totalPrice);
        return "user/order/checkout";
    }

    @GetMapping("/management")
    public String orderManagement(Model model) {
        model.addAttribute("orderList",orderService.getAllOrders());
        return "admin/orders/managementOrders";
    }

    @PostMapping("/updateStatus/{orderId}")
    public String orderUpdateStatus(@PathVariable("orderId") Long orderId,@RequestParam("statusValue") String statusValue,Model model) {
        Order order = orderService.getOrderById(orderId);
        order.setOrderStatus(OrderStatus.valueOf(statusValue));
        orderService.updateStatus(order);
        return "redirect:/orders/management";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("orders") Order order, @RequestParam(value = "voucherCode") String voucherCode) {
        User userLogin = (User) session.getAttribute("userLogin");
        double totalPrice = 0;
        for(ShoppingCart shoppingCart : cartService.findCartByUserId(userLogin.getUserId())){
            totalPrice += shoppingCart.getProduct().getUnitPrice()*shoppingCart.getOrderQuantity();
        }
        if (voucherService.checkVoucherCode(voucherCode)) {
            double discount = voucherService.getVoucherByCode(voucherCode).getDiscount();
            totalPrice = totalPrice*(1-(discount/100));
        }
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(new Date());
        order.setSerialNumber(UUID.randomUUID().toString());
        order.setUser(userService.findById(userLogin.getUserId()));
        order.setOrderStatus(OrderStatus.WAITING);
        orderService.addOrder(order);
        cartDao.removeCartByUserId(order.getUser().getUserId());
        session.setAttribute("orders",order);
        return "redirect:/orders/order-success";
    }

    //chi tiết đơn hàng
    @GetMapping("/order-success")
    public String success(@ModelAttribute Order order) {
        return "user/order/order-success";
    }


}
