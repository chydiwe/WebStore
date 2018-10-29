package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "product_image")
@Entity
public class ProductImage {

    @Id
    @Getter
    @Setter
    @Column(name = "product_id")
    private int productId;

    @Getter
    @Setter
    @Column(name = "product_image")
    private String productImage;

}
