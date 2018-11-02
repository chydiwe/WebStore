package com.jackass.RestAPI.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products  = new ArrayList<>();

    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

}
