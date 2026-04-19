package com.eventmgmt.command;

public class ProcessPaymentCommand implements Command {

    private String method;

    public ProcessPaymentCommand(String method) {
        this.method = method;
    }

    @Override
    public void execute() {
        System.out.println("Payment processed using: " + method);
    }
}