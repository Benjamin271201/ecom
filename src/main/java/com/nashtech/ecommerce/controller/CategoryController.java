package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category-management")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable("id") int id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category addCategory(@RequestParam String name) {
        return categoryService.addCategory(name);
    }

    @PutMapping("/categories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category updateCategoryName(@RequestParam int id, @RequestParam String newName) {
        return categoryService.updateCategoryName(id, newName);
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void toggleCategoryStatus(@PathVariable("id") int id) {
        categoryService.toggleCategoryStatus(id);
    }
}