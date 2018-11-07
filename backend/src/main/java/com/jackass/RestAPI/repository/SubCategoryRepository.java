package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.SubCategory;

import java.util.Set;

public interface SubCategoryRepository {

    Set<SubCategory> findAll();

    Set<SubCategory> findAllByCategoryId(int id);

    SubCategory save(SubCategory subCategory);

    void delete(SubCategory subCategory);

}
