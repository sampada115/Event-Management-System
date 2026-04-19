package com.eventmgmt.model;

import com.eventmgmt.model.enums.RegistrationStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    private String eventId;

    private String seatId;

    public Registration() {}

    private Registration(Builder builder) {
        this.registrationDate = builder.registrationDate;
        this.status = builder.status;
        this.eventId = builder.eventId;
        this.seatId = builder.seatId;
    }

    public static class Builder {

        private LocalDate registrationDate;
        private RegistrationStatus status;
        private String eventId;
        private String seatId;

        public Builder registrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder status(RegistrationStatus status) {
            this.status = status;
            return this;
        }

        public Builder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder seatId(String seatId) {
            this.seatId = seatId;
            return this;
        }

        public Registration build() {
            return new Registration(this);
        }
    }

    public Long getId() { return id; }

    public LocalDate getRegistrationDate() { return registrationDate; }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public RegistrationStatus getStatus() { return status; }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public String getEventId() { return eventId; }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSeatId() { return seatId; }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
}