package com.griddynamics.meetapp.controller;

import com.griddynamics.meetapp.configuration.mapper.CategoryDtoMapper;
import com.griddynamics.meetapp.controller.contract.CategoryDto;
import com.griddynamics.meetapp.model.entity.Category;
import com.griddynamics.meetapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/v1/events/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDtoMapper categoryDtoMapper;

    @GetMapping
    public List<CategoryDto> getCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return categoryDtoMapper.toCategoryDtoList(allCategories);
    }
}
