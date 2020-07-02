package com.griddynamics.meetapp.controller.contract.event.status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class EventStatus {
    @Setter
    private boolean isFavorite = false;

    private final boolean isVisited = false;

    private final boolean isRegistered = false;

    public EventStatus(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}

