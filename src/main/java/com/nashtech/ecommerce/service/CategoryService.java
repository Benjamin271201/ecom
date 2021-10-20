package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        category.setActive(true);
        return categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(int id) {
        Optional<Category> category = categoryRepository.findCategoryById(id);
        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return category;
    }

    public List<Category> getAllCategories() {
            return categoryRepository.findAll();
    }

    public Optional<Category> updateCategoryName(int id, String newName) {
        Optional<Category> category = findCategoryById(id);
        category.ifPresent(cat -> {
            cat.setCategoryName(newName);
            categoryRepository.save(cat);
        });
        return category;
    }

    public Optional<Category> disableCategory(int id) {
        Optional<Category> category = findCategoryById(id);
        category.ifPresent(cat -> {
            cat.setActive(false);
            categoryRepository.save(cat);
        });
        return category;
    }

    public Optional<Category> enableCategory(int id) {
        Optional<Category> category = findCategoryById(id);
        category.ifPresent(cat -> {
            cat.setActive(true);
            categoryRepository.save(cat);
        });
        return category;
    }
}
