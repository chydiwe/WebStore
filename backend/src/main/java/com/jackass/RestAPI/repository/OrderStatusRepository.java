package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.OrderStatus;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = OrderStatus.class, idClass = Integer.class)
public interface OrderStatusRepository {

    OrderStatus getOrderStatusById(int id);

    Set<OrderStatus> findAll();

    OrderStatus save(OrderStatus orderStatus);

    void delete(OrderStatus orderStatus);

}
