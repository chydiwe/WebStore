package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.repository.ManufacturerRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Manufacturer.class, idClass = Integer.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionManufacturerRepository extends ManufacturerRepository {
}
