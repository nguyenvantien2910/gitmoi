package com.ra.projectmd03_nhom4.controller.user;

import com.ra.projectmd03_nhom4.model.Address;
import com.ra.projectmd03_nhom4.service.iplm.AddressServiceImpl;
import com.ra.projectmd03_nhom4.service.iplm.UserServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private UserServiceIplm userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String getFormAddress(Model model) {
        model.addAttribute("address", new Address());
        return "user/address/addressForm";
    }

    @PostMapping()
    public String addAddress(@ModelAttribute("address") Address address, Model model) {
        address.setUser(userService.findById(1L));
        addressService.addNew(address);
        session.setAttribute("address", address);
        return "redirect:/order";
    }

}
