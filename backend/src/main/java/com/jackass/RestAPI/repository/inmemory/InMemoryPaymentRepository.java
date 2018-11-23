package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Payment;
import com.jackass.RestAPI.repository.PaymentRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryPaymentRepository extends InMemoryRepository<Payment> implements PaymentRepository {
    @Override
    public Payment getPaymentById(int id) {
        return table.stream().filter(payment -> payment.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Payment> findAll() {
        return table;
    }

    @Override
    public Payment save(Payment payment) {
        payment.setId(id++);
        table.add(payment);
        return payment;
    }

    @Override
    public void delete(Payment payment) {
        table.remove(payment);
    }
}
