package com.eventmgmt.model;
import com.eventmgmt.model.enums.TicketType;
public class Ticket {

    private String ticketId;
    private Seat seat;
    private TicketType type;

    public Ticket(String ticketId, Seat seat, TicketType type) {
        this.ticketId = ticketId;
        this.seat = seat;
        this.type = type;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Seat getSeat() {
        return seat;
    }

    public TicketType getType() {
        return type;
    }
}