package com.jackass.RestAPI.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "order_info")
@Entity
@IdClass(OrderInfoId.class)
public class OrderInfo implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "order_id")
    @JsonIgnore
    private int orderId;

    @Id
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

}
