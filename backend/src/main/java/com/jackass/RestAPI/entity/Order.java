package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "order_table")
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter(onMethod_=@JsonIgnore)
    @Column(name = "order_id")
    private int id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_id_customer")
    private User userCustomer;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_id_manager")
    private User userManager;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "payment_status_id")
    private PaymentStatus paymentStatus;

    @Getter
    @Setter
    @Column(name = "date_opened")
    private LocalDate dateOpened;

    @Getter
    @Setter
    @Column(name = "date_finished")
    private LocalDate dateFinished;

    @Getter
    @Setter
    @Column(name = "user_comment")
    private String userComment;

    @Getter
    @Setter
    @Column(name = "total_cost")
    private int totalCost;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderInfo> products = new ArrayList<>();

}
