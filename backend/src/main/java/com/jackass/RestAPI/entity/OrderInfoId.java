package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class OrderInfoId implements Serializable {

    @Getter
    @Setter
    private int orderId;

    @Getter
    @Setter
    private Product product;
}
