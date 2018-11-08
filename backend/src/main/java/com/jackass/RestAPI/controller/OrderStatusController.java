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
@RequestMapping("api/order_status")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<OrderStatus>> getDeliveries() {
        return ResponseEntity.ok().body(orderStatusRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addOrderStatus(@RequestParam String name) {
        Set<OrderStatus> orderStatuses = orderStatusRepository.findAll();
        for (OrderStatus os : orderStatuses) {
            if (os.getName().equals(name)) {
                throw new AlreadyExistsException("Such order status name already exists.");
            }
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setName(name);
        orderStatusRepository.save(orderStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteOrderStatus(@RequestParam int id) {
        OrderStatus orderStatus = orderStatusRepository.getOrderStatusById(id);
        if (orderStatus == null) {
            throw new NotFoundException("Such order status id does not exists.");
        }
        orderStatusRepository.delete(orderStatus);
    }
}
