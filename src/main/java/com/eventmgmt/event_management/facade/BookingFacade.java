package com.eventmgmt.event_management.facade;

import com.eventmgmt.event_management.model.*;

public class BookingFacade {

    private Event event;

    public BookingFacade(Event event) {
        this.event = event;
    }

    public boolean checkAvailability() {
        return !event.getAvailableSeats().isEmpty();
    }

    public Ticket bookTicket(String ticketId, TicketType type) {
        for (Seat seat : event.getAvailableSeats()) {
            if (seat.isAvailable()) {
                seat.bookSeat();
                return new Ticket(ticketId, seat, type);
            }
        }
        throw new RuntimeException("No seats available");
    }

    public void cancelTicket(Ticket ticket) {
        ticket.getSeat().releaseSeat();
    }
}