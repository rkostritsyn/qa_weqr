package com.griddynamics.meetapp.repository;

import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.util.EventPublicityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, String> {

    Page<Event> findAllByCategoryPublicityType(Pageable p, EventPublicityType publicityType);

    Page<Event> findAllByCategoryTitleIgnoreCase(Pageable p, String categoryTitle);

    Page<Event> findAllByCategoryTitleIgnoreCaseAndCategoryPublicityType(Pageable p, String categoryTitle, EventPublicityType publicityType);

    Page<Event> findAllByStartDateAfter(Pageable p, Date since);

    Page<Event> findAllByStartDateAfterAndCategoryPublicityType(Pageable p, Date since, EventPublicityType publicityType);

    Page<Event> findAllByStartDateAfterAndCategoryTitleIgnoreCase(Pageable p, Date since, String categoryTitle);

    Page<Event> findAllByStartDateAfterAndCategoryTitleIgnoreCaseAndCategoryPublicityType(Pageable p, Date since, String categoryTitle, EventPublicityType publicityType);

    Page<Event> findAllByRegistrationsUserId(Pageable p, long userId);

    Page<Event> findAllByFavoritesId(Pageable p, long userId);

    Page<Event> findAllByRegistrationsUserIdAndRegistrationsHasVisitedIsTrue(Pageable p, long userId);
}
