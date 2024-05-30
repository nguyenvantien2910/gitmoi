package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.dto.request.EditUserRequest;
import com.ra.projectmd03_nhom4.model.*;
import com.ra.projectmd03_nhom4.service.IBannerService;
import com.ra.projectmd03_nhom4.service.ICategoryService;
import com.ra.projectmd03_nhom4.service.IProductService;
import com.ra.projectmd03_nhom4.service.IUserService;
import com.ra.projectmd03_nhom4.service.iplm.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    HttpSession session;

    @Autowired
    IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/homepage")
    public String homepage(Model model) {
        // Lấy thông tin người dùng từ session
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin != null) {
            List<ShoppingCart> cartList = (List<ShoppingCart>) session.getAttribute("cartList");
            model.addAttribute("cartList", cartList);

            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);

            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("categoryList", categoryList);

            List<Banner> bannerList = bannerService.findBannerToDisplay();
            model.addAttribute("bannerList", bannerList);
        }
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
        // lấy lại thông tin người dùng từ database mới nhất
        User updatedUser = userService.findById(userLogin.getUserId());
        EditUserRequest editUserRequest = EditUserRequest.builder()
                .id(updatedUser.getUserId())
                .fullName(updatedUser.getFullName())
                .username(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .phone(updatedUser.getPhone())
                .address(updatedUser.getAddress())
                .avatar(updatedUser.getAvatar())
                .build();
        model.addAttribute("editUserRequest", editUserRequest);
        return "user/info";
    }

    @PostMapping("/updateInfo/{id}")
    public String updateInfo(@PathVariable Long id, @ModelAttribute("editUserRequest") EditUserRequest editUserRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/user/info";
        } else {
            editUserRequest.setId(id);
            editUserRequest.setUpdatedAt(new Date());
            userService.save(editUserRequest);
            redirectAttributes.addFlashAttribute("message", "Update banner success!");
            return "redirect:/user/info";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmNewPassword") String confirmNewPassword,
                                 RedirectAttributes redirectAttributes) {
        User userLogin = (User) session.getAttribute("userLogin");
        User user = userService.findById(userLogin.getUserId());

        if (!(oldPassword.equals(user.getPassword()))) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu cũ không đúng");
            return "redirect:/user/info";
        }

        if (!newPassword.equals(confirmNewPassword)) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu mới không khớp");
            return "redirect:/user/info";
        }

        user.setPassword(newPassword);
        userService.update(user);

        redirectAttributes.addFlashAttribute("message", "Thay đổi mật khẩu thành công");
        return "redirect:/login";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user/contact";
    }
}
