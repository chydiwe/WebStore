package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Product;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Product.class, idClass = Integer.class)
public interface ProductRepository {

    Product getProductByProductId(int id);

    Product getProductByProductName(String name);

    Product save(Product product);

    Product delete(Product product);

}
