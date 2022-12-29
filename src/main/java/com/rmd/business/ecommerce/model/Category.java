package com.rmd.business.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category_name")
    private @NotNull String categoryName;

    private @NotNull String categoryDescription;

    private @NotNull String categoryImageUrl;


    public Category() {
    }

    public Category(@NotNull String categoryName, @NotNull String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Category(@NotNull String categoryName, @NotNull String categoryDescription, @NotNull String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    @Override
    public String toString() {
        return "User {category id=" + categoryId + ", category name='" + categoryName + "', description='" + categoryDescription + "'}";
    }
}