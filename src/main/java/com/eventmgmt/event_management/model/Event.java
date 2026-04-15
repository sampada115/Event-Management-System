package com.eventmgmt.event_management.model;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String eventId;
    private String name;
    private List<Seat> seats;

    public Event(String eventId, String name, int totalSeats) {
        this.eventId = eventId;
        this.name = name;
        this.seats = new ArrayList<>();

        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat("S" + i));
        }
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();

        for (Seat s : seats) {
            if (s.isAvailable()) {
                available.add(s);
            }
        }
        return available;
    }
}