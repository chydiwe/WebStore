package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Payment;

import java.util.Set;

public interface PaymentRepository {

    Payment getPaymentById(int id);

    Set<Payment> findAll();

    Payment save(Payment payment);

    void delete(Payment payment);

}
