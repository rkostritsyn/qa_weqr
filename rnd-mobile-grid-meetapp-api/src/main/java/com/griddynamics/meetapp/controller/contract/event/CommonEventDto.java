package com.griddynamics.meetapp.controller.contract.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.griddynamics.meetapp.controller.contract.CategoryDto;
import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonEventDto {

    private String id;

    private String title;

    private CategoryDto category;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    private String description;

    private String address;

    private String image;

    private List<CommonUserDto> speakers;
}
