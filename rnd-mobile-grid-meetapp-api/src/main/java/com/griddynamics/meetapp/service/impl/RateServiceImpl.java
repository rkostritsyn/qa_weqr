package com.griddynamics.meetapp.service.impl;

import com.griddynamics.meetapp.model.entity.Rate;
import com.griddynamics.meetapp.repository.RateRepository;
import com.griddynamics.meetapp.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Page<Rate> getAllRatesByEventId(String eventId, Pageable p) {
        return rateRepository.findAllByEventId(p, eventId);
    }

    @Override
    public Page<Rate> getAllRatesByUserId(long userId, Pageable p) {
        return rateRepository.findAllByUserId(p, userId);
    }

    @Override
    public Rate addRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Rate updateRate(Rate rate) {
        return rateRepository.save(rate);
    }
}
