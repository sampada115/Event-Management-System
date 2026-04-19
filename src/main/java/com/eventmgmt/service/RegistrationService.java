package com.eventmgmt.service;

import com.eventmgmt.model.Event;
import com.eventmgmt.model.Registration;
import com.eventmgmt.model.Seat;
import com.eventmgmt.model.enums.RegistrationStatus;
import com.eventmgmt.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventService eventService;

    public Registration confirmRegistration(String eventId, String seatId) {

        Registration reg = new Registration.Builder()
                .registrationDate(LocalDate.now())
                .status(RegistrationStatus.CONFIRMED)
                .eventId(eventId)
                .seatId(seatId)
                .build();

        return registrationRepository.save(reg);
    }

    @Transactional
    public void cancelRegistration(Long id) {

        Registration reg = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        if (reg.getEventId() != null && reg.getSeatId() != null) {

            Event event = eventService.getEventById(reg.getEventId());

            if (event != null) {
                for (Seat seat : event.getSeats()) {
                    if (seat.getSeatId().equals(reg.getSeatId())) {
                        seat.releaseSeat();
                        break;
                    }
                }
            }
        }

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