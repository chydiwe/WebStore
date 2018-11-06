package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Bucket;
import com.jackass.RestAPI.repository.BucketRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryBucketRepository extends InMemoryRepository<Bucket> implements BucketRepository {
    @Override
    public Set<Bucket> findAllByUserId(int userId) {
        return table.stream().filter(bucket -> bucket.getUserId() == userId).collect(Collectors.toSet());
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
