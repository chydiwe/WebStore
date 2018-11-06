package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.OrderStatus;

import java.util.Set;

public interface OrderStatusRepository {

    OrderStatus getOrderStatusById(int id);

    Set<OrderStatus> findAll();

    OrderStatus save(OrderStatus orderStatus);

    void delete(OrderStatus orderStatus);

}
