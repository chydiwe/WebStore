package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/manufacturer")
@CrossOrigin(origins = "http://localhost:3000")
public class ManufacturerController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Manufacturer>> getDeliveries() {
        return ResponseEntity.ok().body(manufacturerRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addManufacturer(@RequestParam String name) {
        Set<Manufacturer> manufacturers = manufacturerRepository.findAll();
        for (Manufacturer m : manufacturers) {
            if (m.getName().equals(name)) {
                throw new AlreadyExistsException("Such manufacturer name already exists.");
            }
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturerRepository.save(manufacturer);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteManufacturer(@RequestParam int id) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Such manufacturer id does not exists.");
        }
        manufacturerRepository.delete(manufacturer);
    }
}
