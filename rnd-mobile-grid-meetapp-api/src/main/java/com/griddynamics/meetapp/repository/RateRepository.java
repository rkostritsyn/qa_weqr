package com.griddynamics.meetapp.repository;

import com.griddynamics.meetapp.model.entity.Event;
import com.griddynamics.meetapp.model.entity.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends PagingAndSortingRepository<Rate, Long> {

    Page<Rate> findAllByEventId(Pageable p, String eventId);

    Page<Rate> findAllByUserId(Pageable p, long userId);
}
