package com.eventmgmt.command;

import com.eventmgmt.service.RegistrationService;

public class RegisterCommand implements Command {

    private RegistrationService registrationService;
    private String eventId;
    private String seatId;

    public RegisterCommand(RegistrationService registrationService,
                           String eventId,
                           String seatId) {
        this.registrationService = registrationService;
        this.eventId = eventId;
        this.seatId = seatId;
    }

    @Override
    public void execute() {
        registrationService.confirmRegistration(eventId, seatId);
    }
}