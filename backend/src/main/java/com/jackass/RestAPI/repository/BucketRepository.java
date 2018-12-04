package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.BucketItem;
import com.jackass.RestAPI.entity.id.BucketId;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = BucketItem.class, idClass = BucketId.class)
public interface BucketRepository {

    void delete(BucketItem bucketItem);

    BucketItem save(BucketItem bucketItem);

}