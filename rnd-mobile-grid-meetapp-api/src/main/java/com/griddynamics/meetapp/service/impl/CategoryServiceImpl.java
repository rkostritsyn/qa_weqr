package com.griddynamics.meetapp.service.impl;

import com.google.common.collect.Lists;
import com.griddynamics.meetapp.model.entity.Category;
import com.griddynamics.meetapp.repository.CategoryRepository;
import com.griddynamics.meetapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return Lists.newArrayList(categoryRepository.findAll());
    }

    @Override
    public List<Category> updateCategories(List<Category> categories) {
        return Lists.newArrayList(categoryRepository.saveAll(categories));
    }
}
