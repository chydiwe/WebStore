package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sub_category")
@Entity
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
