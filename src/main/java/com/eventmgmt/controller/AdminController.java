package com.eventmgmt.controller;

import com.eventmgmt.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/users")
    public String users() {
        return "manage-users";
    }

    @GetMapping("/events")
    public String events(Model model) {

        model.addAttribute(
            "events",
            eventService.getAllEvents()
        );

        return "manage-events";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        eventService.deleteEvent(id);

        return "redirect:/admin/events";
    }
}