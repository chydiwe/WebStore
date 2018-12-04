package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Payment;
import com.jackass.RestAPI.entity.ProductComment;
import com.jackass.RestAPI.entity.id.ProductCommentId;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = Payment.class, idClass = Integer.class)
public interface PaymentRepository {

    Payment getPaymentById(int id);

    Set<Payment> findAll();

    Payment save(Payment payment);

    void delete(Payment payment);

}
