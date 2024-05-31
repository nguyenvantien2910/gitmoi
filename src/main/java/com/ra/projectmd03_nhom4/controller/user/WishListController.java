package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.dao.IWishListDao;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.model.WishList;
import com.ra.projectmd03_nhom4.service.IProductService;
import com.ra.projectmd03_nhom4.service.IUserService;
import com.ra.projectmd03_nhom4.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class WishListController {
    @Autowired
    private IWishListService wishListService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/myWishList")
    public String wishList(Model model, HttpSession session) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            session.setAttribute("activePage", "myWishList");
            return "redirect:/login";
        }

        List<Product> productWishList = wishListService.getAllWishList(userLogin.getUserId());
        model.addAttribute("productWishList", productWishList);
        return "user/favorProduct";
    }


    @GetMapping("/deleteWishList/{id}")
    public String deleteWishList(@PathVariable("id") Long id, @SessionAttribute("userLogin") User user) {
        wishListService.deleteWishList(user.getUserId(), id);
        return "redirect:/user/myWishList";
    }

    @GetMapping("/addWishList/{id}")
    public String addWishList(@PathVariable("id") Long productId, HttpSession session) {

        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {

            return "redirect:/login";
        }

        Product product = productService.findById(productId);
        WishList wishList = new WishList();
        wishList.setProduct(product);
        wishList.setUser(userLogin);
        wishListService.addWishList(wishList);
        return "redirect:/user/myWishList";
    }
}


