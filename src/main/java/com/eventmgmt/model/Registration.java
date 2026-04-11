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

    // default constructor required by JPA
    public Registration() {}

    // private constructor used by Builder
    private Registration(Builder builder) {
        this.registrationDate = builder.registrationDate;
        this.status = builder.status;
    }

    // ===== BUILDER CLASS =====
    public static class Builder {
        private LocalDate registrationDate;
        private RegistrationStatus status;

        public Builder registrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder status(RegistrationStatus status) {
            this.status = status;
            return this;
        }

        public Registration build() {
            return new Registration(this);
        }
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
}