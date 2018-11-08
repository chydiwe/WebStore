package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "category")
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "category_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "category_name")
    private String name;

}
