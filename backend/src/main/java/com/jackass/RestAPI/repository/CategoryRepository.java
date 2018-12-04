package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Category;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = Category.class, idClass = Integer.class)
public interface CategoryRepository {

    Category getCategoryById(int id);

    Set<Category> findAll();

    Category save(Category category);

    void delete(Category category);

}
