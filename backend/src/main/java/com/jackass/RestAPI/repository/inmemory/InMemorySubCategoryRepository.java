package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.SubCategory;
import com.jackass.RestAPI.repository.SubCategoryRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class InMemorySubCategoryRepository extends InMemoryRepository<SubCategory> implements SubCategoryRepository {
    @Override
    public Set<SubCategory> findAll() {
        return table;
    }

    @Override
    public Set<SubCategory> findAllByCategoryId(int id) {
        return table.stream().filter(subCategory -> subCategory.getCategoryId() == id).collect(Collectors.toSet());
    }

    @Override
    public SubCategory save(SubCategory subCategory) {
        table.add(subCategory);
        return subCategory;
    }

    @Override
    public void delete(SubCategory subCategory) {
        table.remove(subCategory);
    }
}
