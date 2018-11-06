package com.jackass.RestAPI.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "bucket")
@Entity
public class Bucket {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private int userId;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product products;

    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

}
