package com.griddynamics.meetapp.service;

import com.griddynamics.meetapp.client.GridEventClient;
import com.griddynamics.meetapp.client.resource.EventResource;
import com.griddynamics.meetapp.model.entity.Category;
import com.griddynamics.meetapp.model.entity.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GridEventsScheduler {
    @Autowired
    private GridEventClient gridEventClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventService eventService;
    @Autowired
    private CategoryService categoryService;

    @Scheduled(cron = "0 0 1 ? * *", zone = "Europe/Moscow") //every day at 1.00 a.m.
    @PostConstruct
    public void execute() {
        List<Event> events = getEventsFromGridApi();
        List<Category> categories = events
                .stream()
                .map(Event::getCategory)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        categoryService.updateCategories(categories);
        eventService.updateEvents(events);
    }

    private List<Event> getEventsFromGridApi() {
        EventResource resource = gridEventClient.getEvents();
        return resource.getItems()
                .stream()
                .map(e ->  modelMapper.map(e, Event.class))
                .collect(Collectors.toList());
    }
}
