package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "product_comment")
@Entity
@IdClass(ProductComment.class)
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
    @JsonIgnore
    private int productId;

    @Id
    @Getter
    @Setter
    @Column(name = "product_comment")
    private String comment;

    @Getter
    @Setter
    @Column(name = "comment_date")
    private LocalDate date;

}
