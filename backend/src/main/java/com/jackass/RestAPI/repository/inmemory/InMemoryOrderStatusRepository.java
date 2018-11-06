package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.OrderStatus;
import com.jackass.RestAPI.repository.OrderStatusRepository;

import java.util.Set;

public class InMemoryOrderStatusRepository extends InMemoryRepository<OrderStatus> implements OrderStatusRepository {
    @Override
    public OrderStatus getOrderStatusById(int id) {
        return table.stream().filter(orderStatus -> orderStatus.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<OrderStatus> findAll() {
        return table;
    }

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        orderStatus.setId(id++);
        table.add(orderStatus);
        return orderStatus;
    }

    @Override
    public void delete(OrderStatus orderStatus) {
        table.remove(orderStatus);
    }
}
