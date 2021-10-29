package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Brand;
import com.nashtech.ecommerce.exception.AlreadyExistsException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    //Error msg
    public static final String BRAND_NOT_FOUND = "Brand not found!";
    public static final String BRAND_ALREADY_EXISTS = "Brand already exists!";

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(String name) {
        if (brandRepository.existsByBrandName(name)) {
            throw new AlreadyExistsException(BRAND_ALREADY_EXISTS);
        }
        Brand brand = new Brand();
        brand.setBrandName(name);
        brand.setActive(true);
        return brandRepository.save(brand);
    }

    public Brand findBrandById(int id) {
        return brandRepository
                .findBrandById(id)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND));
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand updateBrandName(int id, String newName) {
        Brand brand = findBrandById(id);
        brand.setBrandName(newName);
        return brandRepository.save(brand);
    }

    public Brand updateBrandStatus(int id) {
        Brand brand = findBrandById(id);
        boolean isActive = brand.isActive();

        brand.setActive(!isActive);
        return brandRepository.save(brand);
    }
}
