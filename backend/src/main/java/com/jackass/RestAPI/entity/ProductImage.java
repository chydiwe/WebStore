package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "product_image")
@Entity
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
