package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.PaymentStatus;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.PaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/orders/payments/statuses")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentStatusController {

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    //
    //  GET
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<PaymentStatus>> getPaymentStatuses() {
        Set<PaymentStatus> paymentSatuses = paymentStatusRepository.findAll();
        return ResponseEntity.ok().body(paymentSatuses);
    }

    //
    //  POST
    //
    @RequestMapping(method = RequestMethod.POST)
    public void addPaymentStatus(@RequestParam String name) {
        Set<PaymentStatus> statuses = paymentStatusRepository.findAll();
        for (PaymentStatus ps : statuses) {
            if (ps.getName().equals(name)) {
                throw new AlreadyExistsException("Such payment status name already exists.");
            }
        }
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setName(name);
        paymentStatusRepository.save(paymentStatus);
    }

    //
    //  DELETE
    //
    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePaymentStatus(@RequestParam int id) {
        PaymentStatus ps = paymentStatusRepository.getPaymentStatusById(id);
        if (ps == null) {
            throw new NotFoundException("Wrong payment status ID.");
        }
        paymentStatusRepository.delete(ps);
    }

}