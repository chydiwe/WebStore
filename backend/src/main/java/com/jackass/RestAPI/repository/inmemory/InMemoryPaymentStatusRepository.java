package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.PaymentStatus;
import com.jackass.RestAPI.repository.PaymentStatusRepository;

import java.util.Set;

public class InMemoryPaymentStatusRepository extends InMemoryRepository<PaymentStatus> implements PaymentStatusRepository {
    @Override
    public PaymentStatus getPaymentStatusById(int id) {
        return table.stream().filter(paymentStatus -> paymentStatus.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<PaymentStatus> findAll() {
        return table;
    }

    @Override
    public PaymentStatus save(PaymentStatus paymentStatus) {
        paymentStatus.setId(id++);
        table.add(paymentStatus);
        return paymentStatus;
    }

    @Override
    public void delete(PaymentStatus paymentStatus) {
        table.remove(paymentStatus);
    }
}
