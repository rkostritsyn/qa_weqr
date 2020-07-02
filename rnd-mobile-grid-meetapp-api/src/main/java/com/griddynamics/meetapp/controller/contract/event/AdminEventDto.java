package com.griddynamics.meetapp.controller.contract.event;

import com.griddynamics.meetapp.controller.contract.RateDto;
import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminEventDto extends CommonEventDto {

    private List<CommonUserDto> visited;

    private List<RateDto> rates;
}
