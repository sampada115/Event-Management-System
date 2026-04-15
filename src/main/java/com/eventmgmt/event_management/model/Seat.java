package com.eventmgmt.event_management.model;

public class Seat {

    private String seatId;
    private SeatStatus status;

    public Seat(String seatId) {
        this.seatId = seatId;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatId() {
        return seatId;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    public void bookSeat() {
        if (!isAvailable()) {
            throw new RuntimeException("Seat already booked");
        }
        this.status = SeatStatus.BOOKED;
    }

    public void releaseSeat() {
        this.status = SeatStatus.AVAILABLE;
    }
}