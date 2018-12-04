package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ProductImage;
import com.jackass.RestAPI.entity.id.ProductImageId;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = ProductImage.class, idClass = ProductImageId.class)
public interface ProductImageRepository {

    void save(ProductImage productImage);

    void delete(ProductImage productImage);

}
