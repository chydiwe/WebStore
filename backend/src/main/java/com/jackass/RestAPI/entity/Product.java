package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "product")
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "product_id")
    private int productId;
    @Getter
    @Setter
    @Column(name = "product_name")
    private String productName;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @Getter
    @Setter
    @Column(name = "short_info")
    private String shortInfo;
    @Getter
    @Setter
    @Column(name = "cost")
    private int cost;
    @Getter
    @Setter
    @Column(name = "quantity")
    private int quantity;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductComment> comments = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductImage> images = new ArrayList<>();

}