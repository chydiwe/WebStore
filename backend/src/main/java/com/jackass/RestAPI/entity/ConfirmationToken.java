package com.jackass.RestAPI.entity;

import javax.persistence.*;

@Table(catalog = "confirmation_token")
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue
    @Column(name = "confirmation_id")
    private int confirmationId;
    @Column
    private String token;
    @Column(name = "user_id")
    private int userId;

    public int getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(int confirmationId) {
        this.confirmationId = confirmationId;
    }

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
