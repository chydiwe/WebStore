package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ProductImage;

public interface ProductImageRepository {

    void save(ProductImage productImage);

    void delete(ProductImage productImage);

}
