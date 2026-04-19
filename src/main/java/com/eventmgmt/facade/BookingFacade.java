package com.eventmgmt.facade;

import com.eventmgmt.model.Event;
import com.eventmgmt.model.Seat;
import com.eventmgmt.model.Ticket;
import com.eventmgmt.model.enums.TicketType;

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