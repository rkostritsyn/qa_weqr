package com.griddynamics.meetapp.service.impl;

import com.griddynamics.meetapp.model.entity.CompositeKey;
import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.Registration;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.model.exception.AlreadyExistsException;
import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.repository.EventRepository;
import com.griddynamics.meetapp.repository.RegistrationRepository;
import com.griddynamics.meetapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.griddynamics.meetapp.util.Constant.ERRMSG_ENTITY_ALREADY_EXISTS;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional
    public Registration createRegistration(Long userId,  String eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new NotFoundException();
        }
        if (registrationRepository.findByUserIdAndEventId(userId, eventId).isPresent()) {
            throw new AlreadyExistsException(
                    String.format(ERRMSG_ENTITY_ALREADY_EXISTS +
                            "Registry { userId=%d, eventId=%s }", userId, eventId));
        }
        Event eventIdOnly = Event.builder().id(eventId).build();
        User userIdOnly = User.builder().id(userId).build();
        Registration registration = Registration.builder()
                .hasVisited(false)
                .event(eventIdOnly)
                .user(userIdOnly)
                .build();
        return registrationRepository.save(registration);
    }

    @Override
    public Registration getRegistration(Long userId, String eventId) {
        return registrationRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public Registration setRegistrationVisited(Long userId, String eventId) {
        Registration registration =
                registrationRepository.findByUserIdAndEventId(userId, eventId).orElseThrow(NotFoundException::new);

        if (registration.getHasVisited()) {
            throw new AlreadyExistsException();
        }

        registration.setHasVisited(true);
        return registrationRepository.save(registration);
    }

    @Override
    public Page<Registration> getRegistrationsByUserId(Long userId, Pageable p) {
        return registrationRepository.findAllByUserId(p, userId);
    }

    @Override
    public Registration scanQrCode(String qrCode) {
        CompositeKey compositeKey = new CompositeKey(qrCode);
        return setRegistrationVisited(compositeKey.getUserId(), compositeKey.getEventId());
    }
}
