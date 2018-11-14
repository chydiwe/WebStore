package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.entity.id.BucketId;
import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.BucketItem;
import com.jackass.RestAPI.repository.BucketRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = BucketItem.class, idClass = BucketId.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionBucketRepository extends BucketRepository {
}
