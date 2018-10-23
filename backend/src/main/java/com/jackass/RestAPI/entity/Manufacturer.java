package com.jackass.RestAPI.entity;

import javax.persistence.*;

@Table(name = "manufacturer")
@Entity
public class Manufacturer {

    @Id
    @Column(name = "manufacturer_id")
    private int manufacturerId;
    @Column
    private String manufacturerName;
    @Column
    private String manufacturerLogo;
    @Column
    private String manufacturerInfo;

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerInfo() {
        return manufacturerInfo;
    }

    public void setManufacturerInfo(String manufacturerInfo) {
        this.manufacturerInfo = manufacturerInfo;
    }

    public String getManufacturerLogo() {
        return manufacturerLogo;
    }

    public void setManufacturerLogo(String manufacturerLogo) {
        this.manufacturerLogo = manufacturerLogo;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
