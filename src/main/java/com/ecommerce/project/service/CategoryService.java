package com.ecommerce.project.service;

import com.ecommerce.project.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategories(Category category);
    String deleteCategories(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
