package com.rmd.business.ecommerce.service;

import com.rmd.business.ecommerce.model.Category;
import com.rmd.business.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryRepository.findById(categoryID).orElse(null);
        if (category != null) {
            category.setCategoryName(newCategory.getCategoryName());
            category.setCategoryDescription(newCategory.getCategoryDescription());
            category.setCategoryImageUrl(newCategory.getCategoryImageUrl());
            categoryRepository.save(category);
        }
    }
}