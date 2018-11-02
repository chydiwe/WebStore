package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.repository.CategoryRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Category.class, idClass = Integer.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionCategoryRepository extends CategoryRepository {
}
