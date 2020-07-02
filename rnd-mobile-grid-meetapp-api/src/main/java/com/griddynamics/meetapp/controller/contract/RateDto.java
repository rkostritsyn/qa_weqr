package com.griddynamics.meetapp.controller.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {

    private Long userId;

    private String eventId;

    private Integer rating;

    private String comment;

}
