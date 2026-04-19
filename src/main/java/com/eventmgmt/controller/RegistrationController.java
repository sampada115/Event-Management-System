package com.eventmgmt.controller;

import com.eventmgmt.command.CancelRegistrationCommand;
import com.eventmgmt.command.CommandInvoker;
import com.eventmgmt.command.RegisterCommand;
import com.eventmgmt.model.*;
import com.eventmgmt.model.enums.PaymentStatus;
import com.eventmgmt.model.enums.TicketType;
import com.eventmgmt.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

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

        // Command Pattern
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(
    new RegisterCommand(
        registrationService,
        "E101",
        "S1"
    )
);
        invoker.run();

        // Real Event object
        Event event = new Event("E101", "Tech Conference", 10);

        // Book seat
        Seat seat = event.getAvailableSeats().get(0);
        seat.bookSeat();

        // Generate ticket
        Ticket ticket = new Ticket("T" + System.currentTimeMillis(),
                seat,
                TicketType.VIP);

        // Payment object
        Payment payment = new Payment.Builder()
                .amount(999.0)
                .status(PaymentStatus.SUCCESS)
                .build();

        model.addAttribute("message", "Registration confirmed successfully!");
        model.addAttribute("event", event);
        model.addAttribute("seat", seat);
        model.addAttribute("ticket", ticket);
        model.addAttribute("payment", payment);

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

        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(
                new CancelRegistrationCommand(registrationService, id)
        );
        invoker.run();

        model.addAttribute("message",
                "Registration cancelled successfully.");

        model.addAttribute("registrations",
                registrationService.getAllRegistrations());

        return "my-registrations";
    }
}