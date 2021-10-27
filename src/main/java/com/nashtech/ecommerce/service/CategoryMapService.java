package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.repository.CategoryMapRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapService {
    private final CategoryMapRepository categoryMapRepository;

    public CategoryMapService(CategoryMapRepository categoryMapRepository) {
        this.categoryMapRepository = categoryMapRepository;
    }

    public List<Category> getCategoriesByProductId(int productId) {
        return categoryMapRepository.getCategoriesByProductId(productId);
    }

    public List<Product> getProductsByProductId(int categoryId) {
        return categoryMapRepository.getProductsByCategoryId(categoryId);
    }
}
