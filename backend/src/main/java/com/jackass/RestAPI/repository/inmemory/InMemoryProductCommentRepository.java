package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.ProductComment;
import com.jackass.RestAPI.repository.ProductCommentRepository;

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
