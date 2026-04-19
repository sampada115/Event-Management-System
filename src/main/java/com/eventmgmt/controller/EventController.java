package com.eventmgmt.controller;

import com.eventmgmt.command.CommandInvoker;
import com.eventmgmt.command.ProcessPaymentCommand;
import com.eventmgmt.model.Event;
import com.eventmgmt.model.Payment;
import com.eventmgmt.model.Registration;
import com.eventmgmt.model.Seat;
import com.eventmgmt.model.Ticket;
import com.eventmgmt.model.enums.PaymentStatus;
import com.eventmgmt.model.enums.TicketType;
import com.eventmgmt.service.EventService;
import com.eventmgmt.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationService registrationService;

    // Show all events
    @GetMapping
    public String showEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    // Open booking page
    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable String id, Model model) {

        Event event = eventService.getEventById(id);

        model.addAttribute("event", event);
        model.addAttribute("seats", event.getAvailableSeats());

        return "book-event";
    }

    // Confirm booking
    @PostMapping("/book/{id}")
    public String confirmBooking(@PathVariable String id,
                                 @RequestParam String seatId,
                                 @RequestParam String type,
                                 @RequestParam String paymentMethod,
                                 Model model) {

        Event event = eventService.getEventById(id);

        if (event == null) {
            model.addAttribute("error", "Event not found.");
            return "events";
        }

        Seat selectedSeat = null;

        for (Seat seat : event.getAvailableSeats()) {
            if (seat.getSeatId().equals(seatId)) {
                selectedSeat = seat;
                break;
            }
        }

        // Seat not found
        if (selectedSeat == null) {
            model.addAttribute("event", event);
            model.addAttribute("seats", event.getAvailableSeats());
            model.addAttribute("error", "Selected seat is no longer available.");
            return "book-event";
        }

        // Book seat
        selectedSeat.bookSeat();

        // Create ticket
        Ticket ticket = new Ticket(
                "T" + System.currentTimeMillis(),
                selectedSeat,
                TicketType.valueOf(type)
        );

        // Payment command
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(new ProcessPaymentCommand(paymentMethod));
        invoker.run();

        // Payment object
        Payment payment = new Payment.Builder()
                .amount(type.equals("VIP") ? 999 : 499)
                .status(PaymentStatus.SUCCESS)
                .build();

        // Save registration and get object back
        Registration registration =
                registrationService.confirmRegistration(
                        event.getEventId(),
                        selectedSeat.getSeatId()
                );

        // Send data to success page
        model.addAttribute("event", event);
        model.addAttribute("seat", selectedSeat);
        model.addAttribute("ticket", ticket);
        model.addAttribute("payment", payment);
        model.addAttribute("method", paymentMethod);
        model.addAttribute("registration", registration);

        return "booking-success";
    }
}