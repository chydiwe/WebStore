package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.repository.CategoryRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryCategoryRepository extends InMemoryRepository<Category> implements CategoryRepository {
    @Override
    public Category getCategoryById(int id) {
        return table.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Category> findAll() {
        return table;
    }

    @Override
    public Category save(Category category) {
        category.setId(id++);
        table.add(category);
        return category;
    }

    @Override
    public void delete(Category category) {
        table.remove(category);
    }
}
