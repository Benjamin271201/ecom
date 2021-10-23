package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/category/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/category/update")
    public ResponseEntity<Optional<Category>> updateCategoryName(@RequestParam int id, String newName) {
        return ResponseEntity.ok(categoryService.updateCategoryName(id, newName));
    }

    @PutMapping("/category/disable")
    public ResponseEntity<Optional<Category>> disableCategory(@RequestParam int id) {
        return ResponseEntity.ok(categoryService.disableCategory(id));
    }

    @PutMapping("/category/enable")
    public ResponseEntity<Optional<Category>> enableCategory(@RequestParam int id) {
        return ResponseEntity.ok(categoryService.enableCategory(id));
    }
}