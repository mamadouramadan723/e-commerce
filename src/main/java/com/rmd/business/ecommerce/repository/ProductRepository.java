package com.rmd.business.ecommerce.repository;

import com.rmd.business.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
