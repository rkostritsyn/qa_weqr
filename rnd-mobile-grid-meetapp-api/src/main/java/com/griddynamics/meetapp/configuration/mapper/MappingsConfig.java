package com.griddynamics.meetapp.configuration.mapper;

import com.griddynamics.meetapp.client.resource.EventItem;
import com.griddynamics.meetapp.controller.contract.RateDto;
import com.griddynamics.meetapp.controller.contract.TicketDto;
import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.Rate;
import com.griddynamics.meetapp.model.entity.Registration;
import com.griddynamics.meetapp.util.EventPublicityType;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingsConfig {
    @Bean
    public ModelMapper modelMapper() {

        Condition<EventItem, Event> conditionCategoryIsNull = new Condition<EventItem, Event>() {
            public boolean applies(MappingContext<EventItem, Event> context) {
                return context.getSource().getType() != null;
            }
        };

        PropertyMap<Rate, RateDto> rateDtoMapping = new PropertyMap<Rate, RateDto>() {
            protected void configure() {
                map().setEventId(source.getEvent().getId());
                map().setUserId(source.getUser().getId());
            }
        };

        PropertyMap<EventItem, Event> eventResourceMapping = new PropertyMap<EventItem, Event>() {
            protected void configure() {
                map().setId(source.getSlug());
                when(conditionCategoryIsNull).map(source).setCategory(null);
                map().getCategory().setTitle(source.getType());
                map().getCategory().setPublicityType(EventPublicityType.PUBLIC);
                map().getCategory().setId(source.getType());
                map().setAddress(source.getCity().getName());
                map().setImage(source.getCity().getImagePath());
            }
        };

        PropertyMap<Registration, TicketDto> ticketMapping = new PropertyMap<Registration, TicketDto>() {
            protected void configure() {
                map().setQrCode(source.getCompositeKey().toString());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(rateDtoMapping);
        modelMapper.addMappings(eventResourceMapping);
        modelMapper.addMappings(ticketMapping);
        return modelMapper;
    }
}
