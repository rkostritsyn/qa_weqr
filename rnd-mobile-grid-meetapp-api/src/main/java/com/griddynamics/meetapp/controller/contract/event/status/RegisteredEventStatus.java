package com.griddynamics.meetapp.controller.contract.event.status;

import com.griddynamics.meetapp.controller.contract.TicketDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisteredEventStatus extends EventStatus {

    private final boolean isVisited = false;

    private final boolean isRegistered = true;

    private TicketDto ticket;

    public RegisteredEventStatus(TicketDto ticket) {
        this.ticket = ticket;
    }

    public RegisteredEventStatus(boolean isFavorite, TicketDto ticket) {
        super(isFavorite);
        this.ticket = ticket;
    }
}
