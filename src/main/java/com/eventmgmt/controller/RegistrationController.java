package com.eventmgmt.controller;

import com.eventmgmt.model.Registration;
import com.eventmgmt.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    // DIP — depends on RegistrationService abstraction
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/all")
    public String getAllRegistrations(Model model) {
        model.addAttribute("registrations",
                registrationService.getAllRegistrations());
        return "my-registrations";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerForEvent(Model model) {
        Registration reg = registrationService.confirmRegistration();
        model.addAttribute("message", "Registration confirmed! ID: " + reg.getId());
        model.addAttribute("registrations",
                registrationService.getAllRegistrations());
        return "my-registrations";
    }

    @GetMapping("/cancel/{id}")
    public String showCancelPage(@PathVariable Long id, Model model) {
        model.addAttribute("registration",
                registrationService.getById(id));
        return "cancel-confirm";
    }

    @PostMapping("/cancel/{id}")
    public String cancelRegistration(@PathVariable Long id, Model model) {
        registrationService.cancelRegistration(id);
        model.addAttribute("message", "Registration cancelled successfully.");
        model.addAttribute("registrations",
                registrationService.getAllRegistrations());
        return "my-registrations";
    }
}