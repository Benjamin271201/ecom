package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Brand;
import com.nashtech.ecommerce.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(Brand brand) {
        brand.setActive(true);
        return brandRepository.save(brand);
    }
}
