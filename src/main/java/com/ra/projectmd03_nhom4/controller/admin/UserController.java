package com.ra.projectmd03_nhom4.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;


@Controller()
public class UserController {
    @Autowired
    private HttpSession session;

}
