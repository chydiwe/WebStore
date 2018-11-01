package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "order_status")
@Entity
public class OrderStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter(onMethod_=@JsonIgnore)
    @Column(name = "order_status_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "order_status_name")
    @Enumerated(EnumType.STRING)
    private OrderStatusType name;

    private enum OrderStatusType {
        IN_PROGRESS
    }

}
