package com.rmd.business.ecommerce.service;

import com.rmd.business.ecommerce.dto.ProductDto;
import com.rmd.business.ecommerce.model.Category;
import com.rmd.business.ecommerce.model.Product;
import com.rmd.business.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductDescription(productDto.getProductDescription());
        product.setProductImageURL(productDto.getProductImageURL());
        product.setProductName(productDto.getProductName());
        product.setCategory(category);
        product.setProductPrice(productDto.getProductPrice());
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductImageURL(product.getProductImageURL());
        productDto.setProductName(product.getProductName());
        productDto.setCategoryId(product.getCategory().getCategoryId());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductId(product.getProductId());
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // throw an exception if product does not exists
        if (optionalProduct.isEmpty()) {
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setProductDescription(productDto.getProductDescription());
        product.setProductImageURL(productDto.getProductImageURL());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        productRepository.save(product);
    }
}
