package com.eventmgmt.controller;

import com.eventmgmt.model.User;
import com.eventmgmt.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email) {

        // demo role logic
        if(email.equalsIgnoreCase("admin@gmail.com")) {
            return "redirect:/admin/dashboard";
        }

        if(email.equalsIgnoreCase("organizer@gmail.com")) {
            return "redirect:/organizer/dashboard";
        }

        return "redirect:/events";
    }
}