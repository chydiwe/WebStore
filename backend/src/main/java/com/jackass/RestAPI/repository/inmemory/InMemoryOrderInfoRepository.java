package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.OrderInfo;
import com.jackass.RestAPI.repository.OrderInfoRepository;

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
