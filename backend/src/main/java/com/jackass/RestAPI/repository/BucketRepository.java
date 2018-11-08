package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Bucket;

public interface BucketRepository {

    void delete(Bucket bucket);

    Bucket save(Bucket bucket);

}