package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    //Errors
    private static final String PRODUCT_NOT_FOUND = "Product not found!";

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

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
    }

    //
    public void updateProductStock(int productId, int newStock) {
        productRepository.updateProductStock(productId, newStock);
    }
}
