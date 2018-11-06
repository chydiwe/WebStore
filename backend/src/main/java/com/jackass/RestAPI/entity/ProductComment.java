package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "product_comment")
@Entity
public class ProductComment implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private int userId;

    @Id
    @Getter
    @Setter
    @Column(name = "product_id")
    private int productId;

    @Getter
    @Setter
    @Column(name = "product_comment")
    private String comment;

    @Getter
    @Setter
    @Column(name = "comment_date")
    private LocalDate date;

}
