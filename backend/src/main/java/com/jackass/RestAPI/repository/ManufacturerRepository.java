package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Manufacturer;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Manufacturer.class, idClass = Integer.class)
public interface ManufacturerRepository {

    Manufacturer getManufacturerByManufacturerId(int id);

    Manufacturer save(Manufacturer manufacturer);

    Manufacturer delete(Manufacturer manufacturer);

}
