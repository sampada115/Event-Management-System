package com.eventmgmt.controller;

import com.eventmgmt.model.User;
import com.eventmgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(User user) {
        userService.saveUser(user);
        return "login";
    }
}