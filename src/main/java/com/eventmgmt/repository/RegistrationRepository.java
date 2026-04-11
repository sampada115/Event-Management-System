package com.eventmgmt.repository;

import com.eventmgmt.model.Registration;
import com.eventmgmt.model.enums.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistrationRepository
        extends JpaRepository<Registration, Long> {

    List<Registration> findByStatus(RegistrationStatus status);
}