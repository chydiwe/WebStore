package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Delivery;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/delivery")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Delivery>> getDeliveries() {
        return ResponseEntity.ok().body(deliveryRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addDelivery(@RequestParam String name) {
        Set<Delivery> deliveries = deliveryRepository.findAll();
        for (Delivery d : deliveries) {
            if (d.getName().equals(name)) {
                throw new AlreadyExistsException("Such delivery name already exists.");
            }
        }
        Delivery delivery = new Delivery();
        delivery.setName(name);
        deliveryRepository.save(delivery);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteDelivery(@RequestParam int id) {
        Delivery delivery = deliveryRepository.getDeliveryById(id);
        if (delivery == null) {
            throw new NotFoundException("Such delivery id does not exists.");
        }
        deliveryRepository.delete(delivery);
    }
}
