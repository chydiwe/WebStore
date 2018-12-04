package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Delivery;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = Delivery.class, idClass = Integer.class)
public interface DeliveryRepository {

    Delivery getDeliveryById(int id);

    Set<Delivery> findAll();

    Delivery save(Delivery delivery);

    void delete(Delivery delivery);

}
