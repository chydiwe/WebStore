package com.jackass.RestAPI.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "bucket")
@Entity
public class Bucket {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private User user;

    @Getter
    @Setter
    @Column(name = "product_id")
    private List<Product> products;

    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

}
