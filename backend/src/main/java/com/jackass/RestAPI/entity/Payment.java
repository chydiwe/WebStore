package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "payment")
@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter(onMethod_=@JsonIgnore)
    @Column(name = "payment_id")
    private int paymentId;
    @Getter @Setter
    @Column(name = "payment_name")
    private String paymentName;

}
