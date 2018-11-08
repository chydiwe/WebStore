package com.jackass.RestAPI.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "bucket")
@Entity
@IdClass(BucketId.class)
public class Bucket implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    @JsonIgnore
    private int userId;

    @Id
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
