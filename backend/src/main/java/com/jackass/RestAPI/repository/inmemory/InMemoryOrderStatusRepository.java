package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.OrderStatus;
import com.jackass.RestAPI.repository.OrderStatusRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryOrderStatusRepository extends InMemoryRepository<OrderStatus> implements OrderStatusRepository {
    @Override
    public OrderStatus getOrderStatusById(int id) {
        return table.stream().filter(orderStatus -> orderStatus.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<OrderStatus> findAll() {
        return new HashSet<>(table);
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
