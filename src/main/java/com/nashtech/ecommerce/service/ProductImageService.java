package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.repository.ProductImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }
}
