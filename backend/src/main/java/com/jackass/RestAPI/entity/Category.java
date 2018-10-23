package com.jackass.RestAPI.entity;

import javax.persistence.*;

@Table(name = "category")
@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    private int categoryId;
    @Column
    private String categoryName;
    @Column
    private String subCategory;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
