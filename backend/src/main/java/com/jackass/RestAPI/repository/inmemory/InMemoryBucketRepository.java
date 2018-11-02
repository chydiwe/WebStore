package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Bucket;
import com.jackass.RestAPI.repository.BucketRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryBucketRepository extends InMemoryRepository<Bucket> implements BucketRepository {
    @Override
    public Bucket getBucketByUserId(int userId) {
        return table.stream().filter(bucket -> bucket.getUserId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(Bucket bucket) {
        table.remove(bucket);
    }

    @Override
    public Bucket save(Bucket bucket) {
        table.add(bucket);
        return bucket;
    }
}
