package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "category")
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter(onMethod_=@JsonIgnore)
    @Column(name = "category_id")
    private int categoryId;
    @Getter @Setter
    @Column(name = "category_name")
    private String categoryName;
    @Getter @Setter
    @Column(name = "sub_category")
    private String subCategory;

}
