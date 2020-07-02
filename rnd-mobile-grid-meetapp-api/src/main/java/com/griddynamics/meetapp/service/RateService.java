package com.griddynamics.meetapp.service;

import com.griddynamics.meetapp.model.entity.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RateService {

    Page<Rate> getAllRatesByEventId(String eventId, Pageable p);

    Page<Rate> getAllRatesByUserId(long userId, Pageable p);

    Rate addRate(Rate rate);

    Rate updateRate(Rate rate);
}
