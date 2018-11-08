package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.entity.Product;

import java.util.Set;

public interface ProductRepository {

    Product getProductById(int id);

    Product getProductByName(String name);

    Set<Product> findAllByCategory(Category category/*, Pageable pageable*/);

    Set<Product> findAllByManufacturer(Manufacturer manufacturer/*, Pageable pageable*/);

    Set<Product> findAll(/*Pageable pageable*/);

    Product save(Product product);

    void delete(Product product);

}
