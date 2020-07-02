package com.griddynamics.meetapp.configuration.mapper;

import com.griddynamics.meetapp.controller.contract.RateDto;
import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.Rate;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RateDtoMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventRepository eventRepository;

    public RateDto toRateDto(Rate rate) {
        return modelMapper.map(rate, RateDto.class);
    }

    public Rate toRate(RateDto rateDto) {
        return modelMapper.map(rateDto, Rate.class);
    }

    public Rate toRate(RateDto rateDto, User user, String eventId) {
        Rate rate = modelMapper.map(rateDto, Rate.class);
        rate.setUser(user);
        Event event = eventRepository.findById(eventId).orElseThrow(NotFoundException::new);
        rate.setEvent(event);
        return rate;
    }

    public List<RateDto> toRateDtoList(List<Rate> rates) {
        return rates.stream().map(this::toRateDto)
                .collect(Collectors.toList());
    }
}
