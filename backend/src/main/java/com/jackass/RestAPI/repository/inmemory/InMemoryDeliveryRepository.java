package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Delivery;
import com.jackass.RestAPI.repository.DeliveryRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryDeliveryRepository extends InMemoryRepository<Delivery> implements DeliveryRepository {
    @Override
    public Delivery getDeliveryById(int id) {
        return table.stream().filter(delivery -> delivery.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Delivery> findAll() {
        return new HashSet<>(table);
    }

    @Override
    public Delivery save(Delivery delivery) {
        delivery.setId(id++);
        table.add(delivery);
        return delivery;
    }

    @Override
    public void delete(Delivery delivery) {
        table.remove(delivery);
    }
}
