package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.BucketItem;
import com.jackass.RestAPI.repository.BucketRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryBucketRepository extends InMemoryRepository<BucketItem> implements BucketRepository {

    @Override
    public void delete(BucketItem bucketItem) {
        table.remove(bucketItem);
    }

    @Override
    public BucketItem save(BucketItem bucketItem) {
        table.add(bucketItem);
        return bucketItem;
    }
}