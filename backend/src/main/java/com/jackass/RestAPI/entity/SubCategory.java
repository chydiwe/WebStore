package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sub_category")
@Entity
@IdClass(SubCategoryId.class)
public class SubCategory implements Serializable{

    @Id
    @Getter
    @Setter
    @Column(name = "category_id")
    private int categoryId;

    @Id
    @Getter
    @Setter
    @Column(name = "sub_category_name")
    private String name;

}
