package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BucketId implements Serializable {

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private Product product;
}
