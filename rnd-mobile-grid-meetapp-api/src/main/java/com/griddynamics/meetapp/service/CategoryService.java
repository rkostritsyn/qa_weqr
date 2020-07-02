package com.griddynamics.meetapp.service;

import com.griddynamics.meetapp.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> updateCategories(List<Category> categories);
}
