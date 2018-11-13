package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ProductComment;

public interface ProductCommentRepository {

    void save(ProductComment productComment);

    void delete(ProductComment productComment);
}
