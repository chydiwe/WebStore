package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "product_image")
@Entity
@IdClass(ProductImage.class)
public class ProductImage implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "product_id")
    @JsonIgnore
    private int productId;

    @Id
    @Getter
    @Setter
    @Column(name = "product_image")
    private String image;

}
