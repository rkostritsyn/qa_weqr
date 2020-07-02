package com.griddynamics.meetapp.controller.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {

    private long totalCount;
    private List<T> content;
}

