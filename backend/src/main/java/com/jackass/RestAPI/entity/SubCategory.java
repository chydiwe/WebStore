package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sub_category")
@Entity
public class SubCategory implements Serializable{

    @Id
    @Getter
    @Setter(onMethod_=@JsonIgnore)
    @Column(name = "category_id")
    private int categoryId;

    @Id
    @Getter
    @Setter
    @Column(name = "sub_category_name")
    private String name;

}
