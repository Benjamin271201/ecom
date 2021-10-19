package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProduct();
    }

    public List<Product> searchProductsByName(String keyword) {
        return productRepository.getProductByNameContains(keyword);
    }
}
