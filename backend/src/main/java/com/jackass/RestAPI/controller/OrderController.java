package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.*;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@RequestParam int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        return ResponseEntity.ok().body(order);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteOrder(@RequestParam int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        orderRepository.delete(order);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addOrder(@RequestParam int customer,
                         @RequestParam int manager,
                         @RequestParam int delivery,
                         @RequestParam int payment,
                         @RequestParam String comment) {
        User userCustomer = userRepository.getUserById(customer);
        if (userCustomer == null) {
            throw new NotFoundException("Wrong user-customer ID.");
        }

        User userManager = userRepository.getUserById(manager);
        if (userManager == null) {
            throw new NotFoundException("Wrong user-manager ID.");
        }

        Delivery deliveryObj = deliveryRepository.getDeliveryById(delivery);
        if (deliveryObj == null) {
            throw new NotFoundException("Wrong delivery ID.");
        }

        Payment paymentObj = paymentRepository.getPaymentById(payment);
        if (paymentObj == null) {
            throw new NotFoundException("Wrong payment ID.");
        }

        Order order = new Order();
        order.setDateOpened(LocalDate.now());
        order.setDelivery(deliveryObj);
        order.setPayment(paymentObj);
        order.setUserComment(comment);
        orderRepository.save(order);
    }

    @RequestMapping(method = RequestMethod.POST, params = "paymentStatus")
    public void updatePaymentStatus(@RequestParam int id,
                                    @RequestParam int paymentStatus) {
        PaymentStatus paymentStatusObj = paymentStatusRepository.getPaymentStatusById(paymentStatus);
        if (paymentStatusObj == null) {
            throw new NotFoundException("Wrong payment status ID.");
        }

        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }

        order.setPaymentStatus(paymentStatusObj);
        orderRepository.save(order);
    }

    @RequestMapping(method = RequestMethod.POST, params = "orderStatus")
    public void updateOrderStatus(@RequestParam int id,
                                  @RequestParam int orderStatus) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }

        OrderStatus orderStatusObj = orderStatusRepository.getOrderStatusById(orderStatus);
        if (orderStatusObj == null) {
            throw new NotFoundException("Wrong order status ID.");
        }

        order.setOrderStatus(orderStatusObj);
        orderRepository.save(order);
    }

    @RequestMapping(method = RequestMethod.POST, params = "finish")
    public void closeOrder(@RequestParam int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        order.setDateFinished(LocalDate.now());
        orderRepository.save(order);
    }

    @RequestMapping(method = RequestMethod.POST, params = "comment")
    public void changeComment(@RequestParam int id,
                              @RequestParam String comment) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        order.setUserComment(comment);
    }
}
