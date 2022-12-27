package com.rmd.business.ecommerce.service;

import com.rmd.business.ecommerce.dto.ProductDto;
import com.rmd.business.ecommerce.model.Category;
import com.rmd.business.ecommerce.model.Product;
import com.rmd.business.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setProductDescription(productDto.getProductDescription());
        product.setProductImageURL(productDto.getProductImageURL());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductName(productDto.getProductName());
        return product;
    }
    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    // list of all the products
    public List<ProductDto> listProducts() {
        // first fetch all the products
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDTOs = new ArrayList<>();

        for(Product product : products) {
            // for each product change it to DTO
            productDTOs.add(new ProductDto(product));
        }
        return productDTOs;
    }

    // update a product
    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        // set the id for updating
        product.setProductId(productID);
        // update
        productRepository.save(product);
    }
}