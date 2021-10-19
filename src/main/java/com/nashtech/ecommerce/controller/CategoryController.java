package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(Category category) {
        if (categoryService.existsByCategoryName(category.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/disable")
    public ResponseEntity<Optional<Category>> disableCategory(@RequestParam int id) {
        Optional<Category> category = categoryService.findCategoryById(id);
        category.ifPresentOrElse(
                cat -> cat.setActive(false),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
        return ResponseEntity.ok(category);
    }
}