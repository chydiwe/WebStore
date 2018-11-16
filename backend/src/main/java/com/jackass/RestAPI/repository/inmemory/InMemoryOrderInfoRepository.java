package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.OrderInfo;
import com.jackass.RestAPI.repository.OrderInfoRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryOrderInfoRepository extends InMemoryRepository<OrderInfo> implements OrderInfoRepository {
    @Override
    public void save(OrderInfo orderInfo) {
        table.add(orderInfo);
    }

    @Override
    public void delete(OrderInfo orderInfo) {
        table.remove(orderInfo);
    }
}
