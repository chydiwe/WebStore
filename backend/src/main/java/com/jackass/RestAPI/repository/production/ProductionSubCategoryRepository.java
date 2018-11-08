package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.SubCategory;
import com.jackass.RestAPI.entity.SubCategoryId;
import com.jackass.RestAPI.repository.SubCategoryRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = SubCategory.class, idClass = SubCategoryId.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionSubCategoryRepository extends SubCategoryRepository {
}
