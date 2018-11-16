package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.entity.id.ProductCommentId;
import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.ProductComment;
import com.jackass.RestAPI.repository.ProductCommentRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = ProductComment.class, idClass = ProductCommentId.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionProductCommentRepository extends ProductCommentRepository {
}
