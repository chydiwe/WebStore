package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.repository.ProductRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Product.class, idClass = Integer.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionProductRepository extends ProductRepository {
}
