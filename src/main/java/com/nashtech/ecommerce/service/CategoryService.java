package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.exception.AlreadyExistsException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CategoryService {
    //Error msg
    private static final String CATEGORY_NOT_FOUND = "Category not found!";
    private static final String CATEGORY_ALREADY_EXISTS = "Category already exists!";

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(String name) {
        if (categoryRepository.existsByCategoryName(name)) {
            throw new AlreadyExistsException(CATEGORY_ALREADY_EXISTS);
        }
        Category category = new Category();
        category.setCategoryName(name);
        category.setActive(true);
        return categoryRepository.save(category);
    }

    public Category findCategoryById(int id) {
        return categoryRepository
                .findCategoryById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
    }

    public List<Category> getAllCategories() {
            return categoryRepository.findAll().stream().sorted(Comparator.comparingInt(Category::getId)).toList();
    }

    public Category updateCategoryName(int id, String newName) {
        Category category = findCategoryById(id);
        category.setCategoryName(newName);
        return categoryRepository.save(category);
    }

    public void toggleCategoryStatus(int id) {
        Category category = findCategoryById(id);
        boolean currentStatus = category.isActive();
        category.setActive(!currentStatus);
        categoryRepository.save(category);
    }
}
