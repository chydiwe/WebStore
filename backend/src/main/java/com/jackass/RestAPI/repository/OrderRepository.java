package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Order;
import com.jackass.RestAPI.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = Order.class, idClass = Integer.class)
public interface OrderRepository {

    Order getOrderById(int id);

    Set<Order> findAllByUserCustomer(User userCustomer);

    Set<Order> findAllByUserManager(User userManager);

    Order save(Order order);

    void delete(Order order);

}