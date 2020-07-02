package com.griddynamics.meetapp.repository;

import com.griddynamics.meetapp.model.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
}
