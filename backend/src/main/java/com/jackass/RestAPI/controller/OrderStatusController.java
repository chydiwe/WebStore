package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.OrderStatus;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/orders/statuses")
@CrossOrigin(origins = "http://localhost")
public class OrderStatusController {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<OrderStatus>> getOrderStatuses() {
        Set<OrderStatus> orderSatuses = orderStatusRepository.findAll();
        return ResponseEntity.ok().body(orderSatuses);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addOrderStatus(@RequestParam String name) {
        Set<OrderStatus> statuses = orderStatusRepository.findAll();
        for (OrderStatus os : statuses) {
            if (os.getName().equals(name)) {
                throw new AlreadyExistsException("Such order status already exists.");
            }
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setName(name);
        orderStatusRepository.save(orderStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteOrderStatus(@RequestParam int id) {
        OrderStatus os = orderStatusRepository.getOrderStatusById(id);
        if (os == null) {
            throw new NotFoundException("Wrong order status ID.");
        }
        orderStatusRepository.delete(os);
    }


}
