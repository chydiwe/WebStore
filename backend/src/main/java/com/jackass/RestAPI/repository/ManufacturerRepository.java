package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Manufacturer;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = Manufacturer.class, idClass = Integer.class)
public interface ManufacturerRepository {

    Manufacturer getManufacturerById(int id);

    Set<Manufacturer> findAll();

    Manufacturer save(Manufacturer manufacturer);

    void delete(Manufacturer manufacturer);

}
