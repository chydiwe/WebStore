package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Category;
import org.springframework.data.repository.RepositoryDefinition;

public interface CategoryRepository {

    Category getCategoryByName(String name);

    Category save(Category category);

    void delete(Category category);

}
