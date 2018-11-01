package com.jackass.RestAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "group_table")
@Entity
public class GroupTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter(onMethod_=@JsonIgnore)
    @Column(name = "user_group")
    private int id;

    @Getter
    @Setter
    @Column(name = "group_name")
    private String name;

}
