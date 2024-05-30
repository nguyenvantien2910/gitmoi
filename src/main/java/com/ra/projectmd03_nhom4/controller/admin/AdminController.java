package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.dao.iplm.OrderDetailDaoImpl;
import com.ra.projectmd03_nhom4.service.iplm.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    HttpSession session;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailDaoImpl orderDetailDao;


    @GetMapping
    public String homeAdmin(Model model) {
        Integer countSales = orderDetailDao.countSales();
        model.addAttribute("countSales", countSales);
        Integer countTotal = orderDetailDao.sumTotal();
        model.addAttribute("countTotal", countTotal);
        Integer countOrder = orderDetailDao.countOrder();
        model.addAttribute("countOrder", countOrder);
        Integer countVisitor = orderDetailDao.countVisitor();
        model.addAttribute("countVisitor", countVisitor);
        session.setAttribute("activePage", "dashboard");
        return "admin/index";
    }




}
