package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.IdClasses.ProductImageId;
import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.ProductImage;
import com.jackass.RestAPI.repository.ProductImageRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = ProductImage.class, idClass = ProductImageId.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionProductImageRepository extends ProductImageRepository {
}
