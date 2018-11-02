package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Bucket;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Bucket.class, idClass = Integer.class)
public interface BucketRepository {

    Bucket getBucketByUserId(int userId);

    void delete(Bucket bucket);

    Bucket save(Bucket bucket);

}
