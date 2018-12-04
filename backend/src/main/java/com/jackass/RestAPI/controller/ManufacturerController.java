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
    @RequestMapping(
            value = "name={name}&logo={logo}&info={info}",
            method = RequestMethod.POST
    )
    public void addManufacturer(@PathVariable String name,
                                @PathVariable String logo,
                                @PathVariable String info) {
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

    @RequestMapping(
            value = "id={id}&logo={logo}",
            method = RequestMethod.POST
    )
    public void changeLogo(@PathVariable int id,
                           @PathVariable String logo) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }

        manufacturer.setLogo(logo);
        manufacturerRepository.save(manufacturer);
    }

    @RequestMapping(
            value = "id={id}&info={info}",
            method = RequestMethod.POST)

    public void changeInfo(@PathVariable int id,
                           @PathVariable String info) {
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
    @RequestMapping(
            value = "id={id}",
            method = RequestMethod.DELETE
    )
    public void deleteManufacturer(@PathVariable int id) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }
        manufacturerRepository.delete(manufacturer);
    }

}
