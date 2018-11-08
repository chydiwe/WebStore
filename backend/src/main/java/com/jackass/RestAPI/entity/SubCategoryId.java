package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class SubCategoryId implements Serializable {

    @Getter
    @Setter
    private int categoryId;

    @Getter
    @Setter
    private String name;

}
