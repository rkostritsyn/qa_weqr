package com.griddynamics.meetapp.service;

import com.griddynamics.meetapp.controller.contract.PageDto;
import com.griddynamics.meetapp.model.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EventService {

    Page<Event> getPublicEvents(Pageable p);

    Page<Event> getPublicEventsByCategoryTitle(Pageable p, String categoryTitle);

    Page<Event> getAllEvents(Pageable p);

    Page<Event> getAllEventsByCategoryTitle(Pageable p, String categoryTitle);

    Page<Event> getPublicEventsSinceDate(Date date, Pageable p);

    Page<Event> getPublicEventsByCategoryTitleSinceDate(Date date, Pageable p, String categoryTitle);

    Page<Event> getAllEventsSinceDate(Date date, Pageable p);

    Page<Event> getAllEventsByCategoryTitleSinceDate(Date date, Pageable p, String categoryTitle);

    Event addEvent(Event event);

    Event updateEvent(Event event);

    List<Event> updateEvents(List<Event> event);

    Event getEventById(String id);

    Page<Event> getRegisteredEventsByUserId(long userId, Pageable p);

    Page<Event> getFavoritesEventsByUserId(long userId, Pageable p);

    Event addEventToFavorites(String eventId, long userId);

    Event removeEventFromFavorites(String eventId, long userId);

    Page<Event> getVisitedEventsByUserId(long userId, Pageable p);
}
