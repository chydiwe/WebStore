package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.repository.ManufacturerRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryManufacturerRepository extends InMemoryRepository<Manufacturer> implements ManufacturerRepository {

    @Override
    public Manufacturer getManufacturerById(int id) {
        return table.stream().filter(manufacturer -> manufacturer.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Manufacturer> findAll() {
        return table;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        manufacturer.setId(id++);
        table.add(manufacturer);
        return manufacturer;
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        table.remove(manufacturer);
    }
}
