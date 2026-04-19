package com.eventmgmt.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private String name;

    @Transient
    private List<Seat> seats = new ArrayList<>();

    public Event() {
    }

    public Event(String eventId, String name, int totalSeats) {
        this.eventId = eventId;
        this.name = name;

        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat("S" + i));
        }
    }

    public Long getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // returns only available seats
    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();

        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                available.add(seat);
            }
        }

        return available;
    }

    // returns all seats
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}