package com.jackass.RestAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "confirmation_token")
@Entity
public class ConfirmationToken {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private int userId;

    @Getter
    @Setter
    @Column(name = "token")
    private String token;

}
