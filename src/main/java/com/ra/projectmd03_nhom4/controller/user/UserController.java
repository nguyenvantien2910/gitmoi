package com.ra.projectmd03_nhom4.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller("/user")
public class UserController {
    @Autowired
    HttpSession session;

    @GetMapping
    public String index() {
        return "index";
    }
}
