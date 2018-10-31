package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Setter(onMethod_=@JsonIgnore)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Getter
    @Setter
    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Getter
    @Setter
    @Column(name = "manufacturer_logo")
    private String manufacturerLogo;

    @Getter
    @Setter
    @Column(name = "manufacturer_info")
    private String manufacturerInfo;

}
