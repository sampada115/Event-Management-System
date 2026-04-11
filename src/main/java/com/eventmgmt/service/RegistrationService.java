package com.eventmgmt.service;

import com.eventmgmt.model.Registration;
import com.eventmgmt.model.enums.RegistrationStatus;
import com.eventmgmt.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    // DIP — depends on interface, not concrete class
    @Autowired
    private RegistrationRepository registrationRepository;

    // Builder pattern used here to construct Registration object
    public Registration confirmRegistration() {
        Registration reg = new Registration.Builder()
                .registrationDate(LocalDate.now())
                .status(RegistrationStatus.CONFIRMED)
                .build();
        return registrationRepository.save(reg);
    }

    @Transactional
    public void cancelRegistration(Long id) {
        Registration reg = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        reg.setStatus(RegistrationStatus.CANCELLED);
        registrationRepository.save(reg);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    public RegistrationStatus getStatus(Long id) {
        return getById(id).getStatus();
    }
}