package com.jackass.RestAPI.entity.id;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BucketId implements Serializable {

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private int product;
}
