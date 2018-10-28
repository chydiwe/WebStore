package com.jackass.RestAPI.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "product_id")
    private int productId;
    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

}
