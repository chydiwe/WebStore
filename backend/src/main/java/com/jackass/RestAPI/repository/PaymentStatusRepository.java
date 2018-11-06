package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.PaymentStatus;

import java.util.Set;

public interface PaymentStatusRepository {

    PaymentStatus getPaymentStatusById(int id);

    Set<PaymentStatus> findAll();

    PaymentStatus save(PaymentStatus paymentStatus);

    void delete(PaymentStatus paymentStatus);

}
