package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.Order;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.repository.OrderRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryOrderRepository extends InMemoryRepository<Order> implements OrderRepository {

    @Override
    public Order getOrderById(int id) {
        return table.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Order> findAllByUserCustomer(User userCustomer) {
        return table.stream().filter(order -> order.getUserCustomer() == userCustomer).collect(Collectors.toSet());
    }

    @Override
    public Set<Order> findAllByUserManager(User userManager) {
        return table.stream().filter(order -> order.getUserManager() == userManager).collect(Collectors.toSet());
    }

    @Override
    public Order save(Order order) {
        order.setId(id++);
        table.add(order);
        return order;
    }

    @Override
    public void delete(Order order) {
        table.remove(order);
    }
}
