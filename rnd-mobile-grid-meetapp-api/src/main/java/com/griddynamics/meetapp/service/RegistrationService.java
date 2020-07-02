package com.griddynamics.meetapp.service;

import com.griddynamics.meetapp.model.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegistrationService {

    Registration createRegistration(Long userId, String eventId);

    Registration getRegistration(Long userId, String eventId);

    Registration setRegistrationVisited(Long userId, String eventId);

    Page<Registration> getRegistrationsByUserId(Long userId, Pageable p);

    Registration scanQrCode(String qrCode);
}
