package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.exception.AlreadyExistsException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    //Error msg
    private static final String CATEGORY_NOT_FOUND = "Category not found!";
    private static final String CATEGORY_ALREADY_EXISTS = "Category already exists!";

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new AlreadyExistsException(CATEGORY_ALREADY_EXISTS);
        }
        category.setActive(true);
        return categoryRepository.save(category);
    }

    public Category findCategoryById(int id) {
        return categoryRepository
                .findCategoryById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
    }

    public List<Category> getAllCategories() {
            return categoryRepository.findAll();
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

    //TODO: remove
//    public Optional<Category> disableCategory(int id) {
//        Optional<Category> category = findCategoryById(id);
//        category.ifPresent(cat -> {
//            cat.setActive(false);
//            categoryRepository.save(cat);
//        });
//        return category;
//    }
//
//    public Optional<Category> enableCategory(int id) {
//        Optional<Category> category = findCategoryById(id);
//        category.ifPresent(cat -> {
//            cat.setActive(true);
//            categoryRepository.save(cat);
//        });
//        return category;
//    }
}
