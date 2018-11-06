package com.jackass.RestAPI.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "order_info")
@Entity
public class OrderInfo {

    @Id
    @Getter
    @Setter
    @Column(name = "order_id")
    private int orderId;

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
