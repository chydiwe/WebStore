package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.ProductComment;
import com.jackass.RestAPI.repository.ProductCommentRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryProductCommentRepository extends InMemoryRepository<ProductComment> implements ProductCommentRepository {
    @Override
    public void save(ProductComment productComment) {
        table.add(productComment);
    }

    @Override
    public void delete(ProductComment productComment) {
        table.remove(productComment);
    }
}
