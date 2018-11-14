package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.entity.id.OrderInfoId;
import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.OrderInfo;
import com.jackass.RestAPI.repository.OrderInfoRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = OrderInfo.class, idClass = OrderInfoId.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionOrderInfoRepository extends OrderInfoRepository {
}
