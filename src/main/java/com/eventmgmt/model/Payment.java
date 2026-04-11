package com.eventmgmt.model;

import com.eventmgmt.model.enums.PaymentStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    // default constructor
    public Payment() {}

    // private constructor for Builder
    private Payment(Builder builder) {
        this.amount = builder.amount;
        this.status = builder.status;
    }

    // ===== BUILDER =====
    public static class Builder {
        private double amount;
        private PaymentStatus status;

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
}