package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.repository.ManufacturerRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryManufacturerRepository extends InMemoryRepository<Manufacturer> implements ManufacturerRepository {

    @Override
    public Manufacturer getManufacturerByName(String name) {
        return table.stream().filter(manufacturer -> manufacturer.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        manufacturer.setId(id++);
        table.add(manufacturer);
        return manufacturer;
    }

    @Override
    public Manufacturer delete(Manufacturer manufacturer) {
        table.remove(manufacturer);
        return manufacturer;
    }
}
