package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Bucket;
import com.jackass.RestAPI.repository.BucketRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Bucket.class, idClass = Integer.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionBucketRepository extends BucketRepository {
}
