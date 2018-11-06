package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.*;
import com.jackass.RestAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/info")
@CrossOrigin(origins = "http://localhost:3000")
public class InfoController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    @RequestMapping(value = "manufacturers", method = RequestMethod.GET)
    public ResponseEntity<Set<Manufacturer>> getManufacturers() {
        Set<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return ResponseEntity.ok().body(manufacturers);
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public ResponseEntity<Set<Category>> getCategories() {
        Set<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @RequestMapping(value = "groups", method = RequestMethod.GET)
    public ResponseEntity<Set<Group>> getGroups() {
        Set<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok().body(groups);
    }

    @RequestMapping(value = "deliveries", method = RequestMethod.GET)
    public ResponseEntity<Set<Delivery>> getDeliveries() {
        Set<Delivery> deliveries = deliveryRepository.findAll();
        return ResponseEntity.ok().body(deliveries);
    }

    @RequestMapping(value = "order_statuses", method = RequestMethod.GET)
    public ResponseEntity<Set<OrderStatus>> getOrderStatuses() {
        Set<OrderStatus> orderSatuses = orderStatusRepository.findAll();
        return ResponseEntity.ok().body(orderSatuses);
    }

    @RequestMapping(value = "payments", method = RequestMethod.GET)
    public ResponseEntity<Set<Payment>> getPayments() {
        Set<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok().body(payments);
    }

    @RequestMapping(value = "payment_statuses", method = RequestMethod.GET)
    public ResponseEntity<Set<PaymentStatus>> getPaymentStatuses() {
        Set<PaymentStatus> paymentSatuses = paymentStatusRepository.findAll();
        return ResponseEntity.ok().body(paymentSatuses);
    }

}