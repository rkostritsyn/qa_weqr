package com.griddynamics.meetapp.controller.contract;

import com.griddynamics.meetapp.controller.contract.event.CommonEventDto;
import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import com.griddynamics.meetapp.model.entity.CompositeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private CommonEventDto event;

    private CommonUserDto user;

    private String qrCode;
}
