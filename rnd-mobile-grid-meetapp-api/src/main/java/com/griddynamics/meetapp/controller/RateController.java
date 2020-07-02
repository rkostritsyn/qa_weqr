package com.griddynamics.meetapp.controller;

import com.griddynamics.meetapp.configuration.mapper.RateDtoMapper;
import com.griddynamics.meetapp.controller.contract.PageDto;
import com.griddynamics.meetapp.controller.contract.RateDto;
import com.griddynamics.meetapp.model.entity.Rate;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/v1/events")
public class RateController {

    @Autowired
    private RateService rateService;
    @Autowired
    private RateDtoMapper rateDtoMapper;

    @GetMapping(value = "/{eventId}/rates")
    public PageDto<RateDto> getRatesForEvent(@PathVariable("eventId") String eventId,
                                             Pageable p) {
        Page<Rate> allRatesByEventId = rateService.getAllRatesByEventId(eventId, p);
        return new PageDto<>(allRatesByEventId.getTotalElements(), rateDtoMapper.toRateDtoList(allRatesByEventId.getContent()));
    }

    @PostMapping(value = "/{eventId}/rates")
    @ResponseStatus(HttpStatus.CREATED)
    public RateDto addEventRate(@AuthenticationPrincipal User activeUser,
                                @Valid @RequestBody RateDto rateDto,
                                @PathVariable("eventId") String eventId) {
        Rate rate = rateDtoMapper.toRate(rateDto, activeUser, eventId);
        return rateDtoMapper.toRateDto(rateService.addRate(rate));
    }

    @PutMapping(value = "/{eventId}/rates")
    public RateDto updateEventRate(@AuthenticationPrincipal User activeUser,
                                   @Valid @RequestBody RateDto rateDto,
                                   @PathVariable("eventId") String eventId) {
        Rate rate = rateDtoMapper.toRate(rateDto, activeUser, eventId);
        return rateDtoMapper.toRateDto(rateService.updateRate(rate));
    }
}
