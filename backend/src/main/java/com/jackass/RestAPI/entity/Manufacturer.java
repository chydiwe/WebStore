package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "manufacturer")
@Entity
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "manufacturer_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "manufacturer_name")
    private String name;

    @Getter
    @Setter
    @Column(name = "manufacturer_logo")
    private String logo;

    @Getter
    @Setter
    @Column(name = "manufacturer_info")
    private String info;

}
