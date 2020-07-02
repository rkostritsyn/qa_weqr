package com.griddynamics.meetapp.configuration.mapper;

import com.griddynamics.meetapp.controller.contract.CategoryDto;
import com.griddynamics.meetapp.model.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDto> toCategoryDtoList(List<Category> categories) {
        return categories.stream().map(this::toCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto toCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
