package com.griddynamics.meetapp.service.impl;

import com.google.common.collect.Lists;
import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.repository.EventRepository;
import com.griddynamics.meetapp.service.EventService;
import com.griddynamics.meetapp.util.EventPublicityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<Event> getPublicEvents(Pageable p) {
        return eventRepository.findAllByCategoryPublicityType(p, EventPublicityType.PUBLIC);
    }

    @Override
    public Page<Event> getPublicEventsByCategoryTitle(Pageable p, String categoryTitle) {
        return eventRepository.findAllByCategoryTitleIgnoreCaseAndCategoryPublicityType(p, categoryTitle, EventPublicityType.PUBLIC) ;
    }

    @Override
    public Page<Event> getAllEvents(Pageable p) {
        return eventRepository.findAll(p);
    }

    @Override
    public Page<Event> getAllEventsByCategoryTitle(Pageable p, String categoryTitle) {
        return eventRepository.findAllByCategoryTitleIgnoreCase(p, categoryTitle);
    }

    @Override
    public Page<Event> getPublicEventsSinceDate(Date date, Pageable p) {
        return eventRepository.findAllByStartDateAfterAndCategoryPublicityType(p, date, EventPublicityType.PUBLIC);
    }

    @Override
    public Page<Event> getPublicEventsByCategoryTitleSinceDate(Date date, Pageable p, String categoryTitle) {
        return eventRepository.findAllByStartDateAfterAndCategoryTitleIgnoreCaseAndCategoryPublicityType(p, date, categoryTitle, EventPublicityType.PUBLIC);
    }

    @Override
    public Page<Event> getAllEventsSinceDate(Date date, Pageable p) {
        return eventRepository.findAllByStartDateAfter(p, date);
    }

    @Override
    public Page<Event> getAllEventsByCategoryTitleSinceDate(Date date, Pageable p, String categoryTitle) {
        return eventRepository.findAllByStartDateAfterAndCategoryTitleIgnoreCase(p, date, categoryTitle);
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> updateEvents(List<Event> events) {
        return Lists.newArrayList(eventRepository.saveAll(events));
    }

    @Override
    public Event getEventById(String id) {
        return eventRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<Event> getRegisteredEventsByUserId(long userId, Pageable p) {
        return eventRepository.findAllByRegistrationsUserId(p, userId);
    }

    @Override
    public Page<Event> getFavoritesEventsByUserId(long userId, Pageable p) {
        return eventRepository.findAllByFavoritesId(p, userId);
    }

    @Transactional
    @Override
    public Event addEventToFavorites(String eventId, long userId) {
        User userIdOnly = User.builder().id(userId).build();
        Event event = getEventById(eventId);
        event.getFavorites().add(userIdOnly);
        return eventRepository.save(event);
    }

    @Transactional
    @Override
    public Event removeEventFromFavorites(String eventId, long userId) {
        Event event = getEventById(eventId);
        event.getFavorites().removeIf((User user) -> user.getId().equals(userId));
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> getVisitedEventsByUserId(long userId, Pageable p) {
        return eventRepository.findAllByRegistrationsUserIdAndRegistrationsHasVisitedIsTrue(p, userId);
    }
}
