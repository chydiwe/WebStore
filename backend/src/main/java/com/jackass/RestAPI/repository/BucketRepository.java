package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.BucketItem;

public interface BucketRepository {

    void delete(BucketItem bucketItem);

    BucketItem save(BucketItem bucketItem);

}