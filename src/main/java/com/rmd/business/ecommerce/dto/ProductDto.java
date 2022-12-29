package com.rmd.business.ecommerce.dto;

import com.rmd.business.ecommerce.model.Product;

import javax.validation.constraints.NotNull;

public class ProductDto {

    private Integer productId;
    private @NotNull String productName;
    private @NotNull String productImageURL;
    private @NotNull double productPrice;
    private @NotNull String productDescription;
    private @NotNull Integer categoryId;

    public ProductDto(@NotNull String productName, @NotNull String productImageURL, @NotNull double productPrice, @NotNull String productDescription, @NotNull Integer categoryId) {
        this.productName = productName;
        this.productImageURL = productImageURL;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }

    public ProductDto(Product product) {
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductImageURL(product.getProductImageURL());
        this.setProductDescription(product.getProductDescription());
        this.setProductPrice(product.getProductPrice());
        this.setCategoryId(product.getCategory().getCategoryId());
    }

    public ProductDto() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}