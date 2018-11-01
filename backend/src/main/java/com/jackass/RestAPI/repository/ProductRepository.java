package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Product;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Product.class, idClass = Integer.class)
public interface ProductRepository {

    Product getProductById(int id);

    Product getProductByName(String name);

    Product save(Product product);

    Product delete(Product product);

}
