package com.griddynamics.meetapp.controller.contract.event;

import com.griddynamics.meetapp.controller.contract.event.status.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEventDto extends CommonEventDto {

    private EventStatus eventStatus;
}
