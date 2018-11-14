package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.PaymentStatus;
import com.jackass.RestAPI.repository.PaymentStatusRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
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
