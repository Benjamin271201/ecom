package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<Category>> updateCategoryName(int id, String newName) {
        return ResponseEntity.ok(categoryService.updateCategoryName(id, newName));
    }

    @PutMapping("/disable")
    public ResponseEntity<Optional<Category>> disableCategory(@RequestParam int id) {
        return ResponseEntity.ok(categoryService.disableCategory(id));
    }

    @PutMapping("/enable")
    public ResponseEntity<Optional<Category>> enableCategory(@RequestParam int id) {
        return ResponseEntity.ok(categoryService.enableCategory(id));
    }
}