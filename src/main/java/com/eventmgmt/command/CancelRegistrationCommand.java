package com.eventmgmt.command;

import com.eventmgmt.service.RegistrationService;

public class CancelRegistrationCommand implements Command {

    private RegistrationService registrationService;
    private Long id;

    public CancelRegistrationCommand(RegistrationService service, Long id) {
        this.registrationService = service;
        this.id = id;
    }

    @Override
    public void execute() {
        registrationService.cancelRegistration(id);
    }
}