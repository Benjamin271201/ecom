package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        category.setActive(true);
        return categoryRepository.save(category);
    }

    public boolean existsByCategoryName (String name) {
        return categoryRepository.existsByCategoryName(name);
    }

    public Optional<Category> findCategoryByCategoryName(String name) {
        return categoryRepository.findCategoryByCategoryName(name);
    }

    public Optional<Category> findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }
}
