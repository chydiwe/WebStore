package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.ProductImage;
import com.jackass.RestAPI.repository.ProductImageRepository;

public class InMemoryProductImageRepository extends InMemoryRepository<ProductImage> implements ProductImageRepository {
    @Override
    public void save(ProductImage productImage) {
        table.remove(productImage);
    }

    @Override
    public void delete(ProductImage productImage) {
        table.add(productImage);
    }
}
