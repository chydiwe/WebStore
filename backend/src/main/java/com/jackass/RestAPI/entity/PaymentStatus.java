package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "payment_status")
@Entity
public class PaymentStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "payment_status_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "payment_status_name")
    private String name;

}
