package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Category;

import java.util.Set;

public interface CategoryRepository {

    Category getCategoryById(int id);

    Set<Category> findAll();

    Category save(Category category);

    void delete(Category category);

}
