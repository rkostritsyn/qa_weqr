package com.griddynamics.meetapp.controller;

import com.griddynamics.meetapp.configuration.mapper.TicketDtoMapper;
import com.griddynamics.meetapp.controller.contract.TicketDto;
import com.griddynamics.meetapp.model.entity.Registration;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.service.EventService;
import com.griddynamics.meetapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/v1/events")
public class TicketController {

    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private TicketDtoMapper ticketDtoMapper;


    @PostMapping(value = "/{id}/register")
    public TicketDto registerUserToEvent(@AuthenticationPrincipal User activeUser,
                                         @PathVariable("id") String eventId) {
        Registration registration = registrationService.createRegistration(activeUser.getId(), eventId);
        return ticketDtoMapper.toTicketDto(registration);
    }

    @GetMapping(value = "/{id}/ticket")
    public TicketDto getTicketToEvent(@AuthenticationPrincipal User activeUser,
                                      @PathVariable("id") String eventId) {
        Registration registration = registrationService.getRegistration(activeUser.getId(), eventId);
        return ticketDtoMapper.toTicketDto(registration);
    }

    @PostMapping(value = "/{eventId}/admin/scan")
    public TicketDto scanTicket(@PathVariable("eventId") String eventId,
                                @Valid @RequestBody TicketDto ticketDto) {
        Registration registration = registrationService.scanQrCode(ticketDto.getQrCode());
        return ticketDtoMapper.toTicketDto(registration);
    }
}