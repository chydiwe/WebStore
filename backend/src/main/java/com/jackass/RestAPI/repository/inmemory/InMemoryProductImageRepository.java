package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.ProductImage;
import com.jackass.RestAPI.repository.ProductImageRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
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
