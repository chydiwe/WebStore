package com.jackass.RestAPI.entity;

import javax.persistence.*;

@Table(name = "confirmation_token")
@Entity
public class ConfirmationToken {

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
