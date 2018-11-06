package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Manufacturer;

import java.util.Set;

public interface ManufacturerRepository {

    Manufacturer getManufacturerById(int id);

    Set<Manufacturer> findAll();

    Manufacturer save(Manufacturer manufacturer);

    void delete(Manufacturer manufacturer);

}
