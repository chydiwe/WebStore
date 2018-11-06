package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Bucket;

import java.util.Set;

public interface BucketRepository {

    Set<Bucket> findAllByUserId(int userId);

    void delete(Bucket bucket);

    Bucket save(Bucket bucket);

}
