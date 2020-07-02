package com.griddynamics.meetapp.configuration.mapper;

import com.griddynamics.meetapp.controller.contract.RateDto;
import com.griddynamics.meetapp.controller.contract.TicketDto;
import com.griddynamics.meetapp.controller.contract.event.AdminEventDto;
import com.griddynamics.meetapp.controller.contract.event.CommonEventDto;
import com.griddynamics.meetapp.controller.contract.event.UserEventDto;
import com.griddynamics.meetapp.controller.contract.event.status.EventStatus;
import com.griddynamics.meetapp.controller.contract.event.status.RegisteredEventStatus;
import com.griddynamics.meetapp.controller.contract.event.status.VisitedEventStatus;
import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.Rate;
import com.griddynamics.meetapp.model.entity.Registration;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventDtoMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventService eventService;

    public UserEventDto toUserEventDto(Event event, User user) {
        UserEventDto userEventDto = modelMapper.map(event, UserEventDto.class);
        userEventDto.setEventStatus(getEventStatus(event, user));
        return userEventDto;
    }

    public List<UserEventDto> toUserEventDtoList(List<Event> events, User user) {
        return events.stream().map(x -> toUserEventDto(x, user))
                .collect(Collectors.toList());
    }

    public List<CommonEventDto> toCommonEventDtoList(List<Event> events) {
        return events.stream().map(this::toCommonEventDto)
                .collect(Collectors.toList());
    }

    public CommonEventDto toCommonEventDto(Event event) {
        return modelMapper.map(event, CommonEventDto.class);
    }

    public AdminEventDto toAdminEventDto(Event event) {
        return modelMapper.map(event, AdminEventDto.class);
    }

    public Event toEvent(CommonEventDto commonEventDto) {
        return modelMapper.map(commonEventDto, Event.class);
    }

    public Event toUpdatedEvent(CommonEventDto commonEventDto) {
        Event event = eventService.getEventById(commonEventDto.getId());
        modelMapper.map(commonEventDto, event);
        return event;
    }

    public EventStatus getEventStatus(Event event, User user) {
        EventStatus eventStatus = new EventStatus();
        if (user == null) {
            return eventStatus;
        }

        for (Registration registration : event.getRegistrations()) {
            if (registration.getUser().getId().equals(user.getId()) && registration.getHasVisited()) {
                RateDto rateDto = null;
                for (Rate rate : event.getRates()) {
                    if (rate.getUser().getId().equals(user.getId())) {
                        rateDto = modelMapper.map(rate, RateDto.class);
                        break;
                    }
                }
                eventStatus = new VisitedEventStatus(rateDto);
                break;
            }
            if (registration.getUser().getId().equals(user.getId())) {
                eventStatus = new RegisteredEventStatus(modelMapper.map(registration, TicketDto.class));
                break;
            }
        }

        for (User favorite : event.getFavorites())
            if (favorite.getId().equals(user.getId()))
                eventStatus.setFavorite(true);

        return eventStatus;
    }

}
