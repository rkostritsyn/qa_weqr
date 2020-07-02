package com.griddynamics.meetapp.controller.contract.event.status;

import com.griddynamics.meetapp.controller.contract.RateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VisitedEventStatus extends EventStatus {

    private final boolean isVisited = true;

    private final boolean isRegistered = true;

    private RateDto rate;

    public VisitedEventStatus(RateDto rate) {
        this.rate = rate;
    }

    public VisitedEventStatus(boolean isFavorite, RateDto rate) {
        super(isFavorite);
        this.rate = rate;
    }
}
