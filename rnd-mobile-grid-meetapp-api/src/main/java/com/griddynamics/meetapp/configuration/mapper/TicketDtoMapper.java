package com.griddynamics.meetapp.configuration.mapper;

import com.griddynamics.meetapp.controller.contract.TicketDto;
import com.griddynamics.meetapp.model.entity.Registration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TicketDto toTicketDto(Registration registration) {
        return modelMapper.map(registration, TicketDto.class);
    }
}
