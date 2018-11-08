package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.*;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/payments/statuses")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentStatusController {

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<PaymentStatus>> getPaymentStatuses() {
        Set<PaymentStatus> paymentSatuses = paymentStatusRepository.findAll();
        return ResponseEntity.ok().body(paymentSatuses);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addPaymentStatus(@RequestParam String name) {
        Set<PaymentStatus> statuses = paymentStatusRepository.findAll();
        for (PaymentStatus ps : statuses) {
            if (ps.getStatus().equals(name)) {
                throw new AlreadyExistsException("Such payment status name already exists.");
            }
        }
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setStatus(name);
        paymentStatusRepository.save(paymentStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePaymentStatus(@RequestParam int paymentStatusId) {
        PaymentStatus ps = paymentStatusRepository.getPaymentStatusById(paymentStatusId);
        if (ps == null) {
            throw new NotFoundException("Wrong payment status ID.");
        }
        paymentStatusRepository.delete(ps);
    }

}