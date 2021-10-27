package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.service.CategoryMapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryMapController {
    private final CategoryMapService categoryMapService;

    public CategoryMapController(CategoryMapService categoryMapService) {
        this.categoryMapService = categoryMapService;
    }

    @GetMapping("/catmap/products/")
    public List<Product> getProductsByCategoryId(@RequestParam int categoryId) {
        return categoryMapService.getProductsByProductId(categoryId);
    }

    @GetMapping("/catmap/categories")
    public List<Category> getCategoriesByProductId(int productId) {
        return categoryMapService.getCategoriesByProductId(productId);
    }
}
