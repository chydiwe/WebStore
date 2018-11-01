package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "delivery")
@Entity
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter(onMethod_=@JsonIgnore)
    @Column(name = "delivery_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "delivery_name")
    @Enumerated(EnumType.STRING)
    private DeliveryType name;

    private enum DeliveryType {

        SELF_DELIVERY

    }

}
