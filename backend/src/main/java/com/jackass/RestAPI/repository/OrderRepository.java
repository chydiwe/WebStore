package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Order;
import com.jackass.RestAPI.entity.User;

import java.util.Set;

public interface OrderRepository {

    Order getOrderById(int id);

    Set<Order> findAllByUserCustomer(User userCustomer);

    Set<Order> findAllByUserManager(User userManager);

    Order save(Order order);

    void delete(Order order);

}