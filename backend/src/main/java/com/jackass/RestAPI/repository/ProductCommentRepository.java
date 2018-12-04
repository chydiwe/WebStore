package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ProductComment;
import com.jackass.RestAPI.entity.id.ProductCommentId;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = ProductComment.class, idClass = ProductCommentId.class)
public interface ProductCommentRepository {

    void save(ProductComment productComment);

    void delete(ProductComment productComment);
}
