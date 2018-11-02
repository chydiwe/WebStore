package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "user_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter(onMethod_={@JsonIgnore})
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "surname")
    private String surname;

    @Getter
    @Setter
    @Column(name = "patronymic")
    private String patronymic;

    @Getter
    @Setter(onMethod_=@JsonIgnore)
    @OneToOne
    @JoinColumn(name = "user_group")
    private GroupTable group;

    @Getter
    @Setter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Bucket> products = new ArrayList<>();

}
