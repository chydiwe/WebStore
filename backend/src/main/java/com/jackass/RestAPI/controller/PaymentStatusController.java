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
@RequestMapping("api/payment_status")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentStatusController {
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<PaymentStatus>> getDeliveries() {
        return ResponseEntity.ok().body(paymentStatusRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addPaymentStatus(@RequestParam String name) {
        Set<PaymentStatus> paymentStatuses = paymentStatusRepository.findAll();
        for (PaymentStatus ps : paymentStatuses) {
            if (ps.getName().equals(name)) {
                throw new AlreadyExistsException("Such payment status name already exists.");
            }
        }
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setName(name);
        paymentStatusRepository.save(paymentStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePaymentStatus(@RequestParam int id) {
        PaymentStatus paymentStatus = paymentStatusRepository.getPaymentStatusById(id);
        if (paymentStatus == null) {
            throw new NotFoundException("Such payment status id does not exists.");
        }
        paymentStatusRepository.delete(paymentStatus);
    }
}
