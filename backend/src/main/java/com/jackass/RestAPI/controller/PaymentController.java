package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Payment;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/orders/payments")
//@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Payment>> getPayments() {
        Set<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok().body(payments);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addPayment(@RequestParam String name) {
        Set<Payment> payments = paymentRepository.findAll();
        for (Payment p : payments) {
            if (p.getName().equals(name)) {
                throw new AlreadyExistsException("Such payment name already exists.");
            }
        }
        Payment payment = new Payment();
        payment.setName(name);
        paymentRepository.save(payment);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePayment(@RequestParam int id) {
        Payment payment = paymentRepository.getPaymentById(id);
        if (payment == null) {
            throw new NotFoundException("Wrong payment ID.");
        }
        paymentRepository.delete(payment);
    }
}
