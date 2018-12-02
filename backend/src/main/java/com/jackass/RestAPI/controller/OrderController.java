package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.*;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private BucketRepository bucketRepository;

    //
    //  GET
    //
    @RequestMapping(
            value = "id={id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        return ResponseEntity.ok().body(order);
    }

    @RequestMapping(
            value = "userId={userId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<Order>> getUserOrders(@PathVariable int userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }
        Set<Order> orders = orderRepository.findAllByUserCustomer(user);
        return ResponseEntity.ok().body(orders);
    }

    //
    //  POST
    //
    @RequestMapping(
            value = "customer={}&delivery={}&payment={}&comment={}",
            method = RequestMethod.POST
    )
    public void addOrder(@PathVariable int customer,
                         @PathVariable int delivery,
                         @PathVariable int payment,
                         @PathVariable String comment) {
        User userCustomer = userRepository.getUserById(customer);
        if (userCustomer == null) {
            throw new NotFoundException("Wrong user-customer ID.");
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

        Set<OrderInfo> products = new HashSet<>();

        Set<BucketItem> bucket = userCustomer.getProducts();

        for (BucketItem bi : bucket) {
            OrderInfo tmp = new OrderInfo();
            tmp.setProduct(bi.getProduct());
            tmp.setAmount(bi.getAmount());
            products.add(tmp);
            bucketRepository.delete(bi);
        }
        order.setProducts(products);

        orderRepository.save(order);
    }

    @RequestMapping(
            value = "id={id}&manager={manager}",
            method = RequestMethod.POST
    )
    public void changeManager(@PathVariable int id,
                              @PathVariable int manager) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        User user = userRepository.getUserById(manager);
        if (user == null) {
            throw new NotFoundException("Wrong manager ID.");
        }
        order.setUserManager(user);
        orderRepository.save(order);
    }

    @RequestMapping(
            value = "id={id}&paymentStatus={paymentStatus}",
            method = RequestMethod.POST, params = "paymentStatus"
    )
    public void updatePaymentStatus(@PathVariable int id,
                                    @PathVariable int paymentStatus) {
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

    @RequestMapping(
            value = "id={id}&orderStatus={orderStatus}",
            method = RequestMethod.POST, params = "orderStatus"
    )
    public void updateOrderStatus(@PathVariable int id,
                                  @PathVariable int orderStatus) {
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

    @RequestMapping(
            value = "id={id}",
            method = RequestMethod.POST
    )
    public void closeOrder(@PathVariable int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        order.setDateFinished(LocalDate.now());
        orderRepository.save(order);
    }

    @RequestMapping(
            value = "id={id}&comment={comment}",
            method = RequestMethod.POST
    )
    public void changeComment(@PathVariable int id,
                              @PathVariable String comment) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        order.setUserComment(comment);
        orderRepository.save(order);
    }

    //
    //  DELETE
    //
    @RequestMapping(
            value = "id={id}",
            method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NotFoundException("Wrong order ID.");
        }
        orderRepository.delete(order);
    }
}
