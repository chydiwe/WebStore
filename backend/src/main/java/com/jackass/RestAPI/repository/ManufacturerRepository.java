package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Manufacturer;
import org.springframework.data.repository.RepositoryDefinition;

public interface ManufacturerRepository {

    Manufacturer getManufacturerByName(String name);

    Manufacturer save(Manufacturer manufacturer);

    Manufacturer delete(Manufacturer manufacturer);

}
