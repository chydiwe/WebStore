package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Category;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Category.class, idClass = Integer.class)
public interface CategoryRepository {

    Category getCategoryByCategoryId(int id);

    Category save(Category category);

}
