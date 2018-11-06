package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Delivery;

import java.util.Set;

public interface DeliveryRepository {

    Delivery getDeliveryById(int id);

    Set<Delivery> findAll();

    Delivery save(Delivery delivery);

    void delete(Delivery delivery);

}
