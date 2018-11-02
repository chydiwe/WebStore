package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.entity.Product;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

public interface ProductRepository {

    Product getProductById(int id);

    Product getProductByName(String name);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByManufacturer(Manufacturer manufacturer);

    Product save(Product product);

    Product delete(Product product);

}
