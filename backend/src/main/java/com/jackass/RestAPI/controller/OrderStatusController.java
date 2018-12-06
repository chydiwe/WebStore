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
@CrossOrigin(origins = "http://localhost:3000")
public class OrderStatusController {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    //
    //  GET
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<OrderStatus>> getOrderStatuses() {
        Set<OrderStatus> orderSatuses = orderStatusRepository.findAll();
        return ResponseEntity.ok().body(orderSatuses);
    }

    //
    //  POST
    //
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

    //
    //  DELETE
    //
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteOrderStatus(@RequestParam int id) {
        OrderStatus os = orderStatusRepository.getOrderStatusById(id);
        if (os == null) {
            throw new NotFoundException("Wrong order status ID.");
        }
        orderStatusRepository.delete(os);
    }


}
