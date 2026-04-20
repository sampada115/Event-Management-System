package com.eventmgmt.controller;

import com.eventmgmt.model.Event;
import com.eventmgmt.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private EventService eventService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "organizer-dashboard";
    }

    @GetMapping("/create")
    public String createPage() {
        return "create-event";
    }

    @PostMapping("/create")
    public String saveEvent(@RequestParam String name,
                            @RequestParam int seats,
                            Model model) {

        Event event = new Event(
                "E" + System.currentTimeMillis(),
                name,
                seats
        );

        eventService.saveEvent(event);

        model.addAttribute("message", "Event created successfully!");
        model.addAttribute("events", eventService.getAllEvents());

        return "my-events";
    }

    @GetMapping("/my-events")
    public String myEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "my-events";
    }
}