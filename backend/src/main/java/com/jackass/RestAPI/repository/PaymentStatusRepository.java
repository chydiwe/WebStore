package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.PaymentStatus;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = PaymentStatus.class, idClass = Integer.class)
public interface PaymentStatusRepository {

    PaymentStatus getPaymentStatusById(int id);

    Set<PaymentStatus> findAll();

    PaymentStatus save(PaymentStatus paymentStatus);

    void delete(PaymentStatus paymentStatus);

}
