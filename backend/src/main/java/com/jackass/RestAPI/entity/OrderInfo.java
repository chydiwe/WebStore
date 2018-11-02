package com.jackass.RestAPI.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>();

    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

}
