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
@RequestMapping("/api/products/manufacturers")
@CrossOrigin(origins = "http://localhost:3000")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    //
    //  GET
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Manufacturer>> getManufacturers() {
        Set<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return ResponseEntity.ok().body(manufacturers);
    }

    //
    //  POST
    //
    @RequestMapping(method = RequestMethod.POST, params = {"name", "logo", "info"})
    public void addManufacturer(@RequestParam String name,
                                @RequestParam String logo,
                                @RequestParam String info) {
        Set<Manufacturer> manufacturers = manufacturerRepository.findAll();
        for (Manufacturer m : manufacturers) {
            if (m.getName().equals(name)) {
                throw new AlreadyExistsException("Such manufacturer name already exists.");
            }
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setLogo(logo);
        manufacturer.setInfo(info);
        manufacturerRepository.save(manufacturer);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"id", "logo"})
    public void changeLogo(@RequestParam int id,
                           @RequestParam String logo) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }

        manufacturer.setLogo(logo);
        manufacturerRepository.save(manufacturer);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"id", "info"})
    public void changeInfo(@RequestParam int id,
                           @RequestParam String info) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }

        manufacturer.setLogo(info);
        manufacturerRepository.save(manufacturer);
    }

    //
    //  DELETE
    //
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteManufacturer(@RequestParam int id) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }
        manufacturerRepository.delete(manufacturer);
    }

}
