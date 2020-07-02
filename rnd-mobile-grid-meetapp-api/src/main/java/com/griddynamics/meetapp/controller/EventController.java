package com.griddynamics.meetapp.controller;

import com.griddynamics.meetapp.configuration.mapper.EventDtoMapper;
import com.griddynamics.meetapp.configuration.mapper.UserDtoMapper;
import com.griddynamics.meetapp.controller.contract.PageDto;
import com.griddynamics.meetapp.controller.contract.event.AdminEventDto;
import com.griddynamics.meetapp.controller.contract.event.CommonEventDto;
import com.griddynamics.meetapp.controller.contract.event.UserEventDto;
import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.security.Role;
import com.griddynamics.meetapp.service.EventService;
import com.griddynamics.meetapp.service.RegistrationService;
import com.griddynamics.meetapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EventDtoMapper eventDtoMapper;
    @Autowired
    private UserDtoMapper userDtoMapper;

    @GetMapping
    public PageDto<UserEventDto> getEvents(@AuthenticationPrincipal User activeUser,
                                           @RequestParam(required = false) String category,
                                           @RequestParam(required = false) Long since,
                                           Pageable p) {
        Page<Event> events;
        if (activeUser == null || activeUser.getRole() == Role.USER) { //TODO split for two methods
            if (since != null) {
                Date d = new Date(since);
                events = (category == null) ?
                        eventService.getPublicEventsSinceDate(d, p) :
                        eventService.getPublicEventsByCategoryTitleSinceDate(d, p, category);
            } else {
                events = (category == null) ?
                        eventService.getPublicEvents(p) :
                        eventService.getPublicEventsByCategoryTitle(p, category);
            }
        } else {
            if (since != null) {
                Date d = new Date(since);
                events = (category == null) ?
                        eventService.getAllEventsSinceDate(d, p) :
                        eventService.getAllEventsByCategoryTitleSinceDate(d, p, category);
            } else {
                events = (category == null) ?
                        eventService.getAllEvents(p) :
                        eventService.getAllEventsByCategoryTitle(p, category);
            }
        }
        List<UserEventDto> userEventDtoList = eventDtoMapper.toUserEventDtoList(events.getContent(), activeUser);
        long totalElements = events.getTotalElements();
        return new PageDto<>(totalElements, userEventDtoList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonEventDto addEvent(@Valid @RequestBody CommonEventDto commonEventDto) {
        Event eventSrc = eventDtoMapper.toEvent(commonEventDto);
        Event eventUpd = eventService.addEvent(eventSrc);
        return eventDtoMapper.toCommonEventDto(eventUpd);
    }

    @PutMapping
    public CommonEventDto updateEvent(@Valid @RequestBody CommonEventDto commonEventDto) {
        Event eventSrc = eventDtoMapper.toUpdatedEvent(commonEventDto);
        Event eventUpd = eventService.updateEvent(eventSrc);
        return eventDtoMapper.toCommonEventDto(eventUpd);
    }

    @GetMapping(value = "/{eventId}")
    public UserEventDto getEvent(@AuthenticationPrincipal User activeUser,
                                 @PathVariable("eventId") String eventId) {
        return eventDtoMapper.toUserEventDto(eventService.getEventById(eventId),activeUser);
    }

    @GetMapping(value = "/registered")
    public PageDto<UserEventDto> getRegisteredEvents(@AuthenticationPrincipal User activeUser,
                                                            Pageable p) {
        Page<Event> events = eventService.getRegisteredEventsByUserId(activeUser.getId(), p);
        return new PageDto<>(events.getTotalElements(), eventDtoMapper.toUserEventDtoList(events.getContent(), activeUser));
    }

    @GetMapping(value = "/favorites")
    public PageDto<UserEventDto> getFavoriteEvents(@AuthenticationPrincipal User activeUser,
                                                   Pageable p) {
        Page<Event> userFavoriteEvents = eventService.getFavoritesEventsByUserId(activeUser.getId(), p);
        return new PageDto<>(userFavoriteEvents.getTotalElements(), eventDtoMapper.toUserEventDtoList(userFavoriteEvents.getContent(), activeUser));
    }

    @GetMapping(value = "/{eventId}/admin")
    public AdminEventDto getAdminEvent(@PathVariable("eventId") String eventId) {
        Event event = eventService.getEventById(eventId);
        List<User> usersVisitedEvent = userService.getVisitorsForEvent(eventId); //TODO use mapper here
        AdminEventDto adminEventDto = eventDtoMapper.toAdminEventDto(event);
        List<CommonUserDto> commonUserDtos = userDtoMapper.toCommonUserDtoList(usersVisitedEvent);
        adminEventDto.setVisited(commonUserDtos);
        return adminEventDto;
    }

    @GetMapping(value = "/visited")
    public PageDto<UserEventDto> getVisitedEvents(@AuthenticationPrincipal User activeUser,
                                                  Pageable p){
        Page<Event> events = eventService.getVisitedEventsByUserId(activeUser.getId(), p);
        return new PageDto<>(events.getTotalElements(), eventDtoMapper.toUserEventDtoList(events.getContent(), activeUser));
    }

    @PostMapping(value = "/{eventId}/favorites")
    public UserEventDto addEventToFavorite(@AuthenticationPrincipal User activeUser,
                                           @PathVariable("eventId") String eventId) {
        Event eventAddedToFavorites = eventService.addEventToFavorites(eventId, activeUser.getId());
        return eventDtoMapper.toUserEventDto(eventAddedToFavorites, activeUser);
    }

    @DeleteMapping(value = "/{eventId}/favorites")
    public UserEventDto removeEventFromFavorites(@AuthenticationPrincipal User activeUser,
                                                   @PathVariable("eventId") String eventId) {
        Event eventRemovedFromFavorites = eventService.removeEventFromFavorites(eventId, activeUser.getId());
        return eventDtoMapper.toUserEventDto(eventRemovedFromFavorites, activeUser);
    }
}
