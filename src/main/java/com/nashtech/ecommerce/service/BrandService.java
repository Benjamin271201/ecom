package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Brand;
import com.nashtech.ecommerce.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(Brand brand) {
        if (brandRepository.existsByBrandName(brand.getBrandName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        brand.setActive(true);
        return brandRepository.save(brand);
    }

    public Optional<Brand> findBrandById(int id) {
        Optional<Brand> brand = brandRepository.findBrandById(id);
        if (brand.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return brand;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> updateBrandName(int id, String newName) {
        Optional<Brand> brand = findBrandById(id);
        brand.ifPresent(br -> {
            br.setBrandName(newName);
            brandRepository.save(br);
        });
        return brand;
    }

    public Optional<Brand> toggleBrandStatus(int id) {
        Optional<Brand> brand = findBrandById(id);
        brand.ifPresent(br -> {
            boolean isActive = br.isActive();
            br.setActive(!isActive);
            brandRepository.save(br);
        });
        return brand;
    }

//    public Optional<Brand> disableBrand(int id) {
//        Optional<Brand> brand = findBrandById(id);
//        brand.ifPresent(br -> {
//            br.setActive(false);
//            brandRepository.save(br);
//        });
//        return brand;
//    }
//
//    public Optional<Brand> enableBrand(int id) {
//        Optional<Brand> brand = findBrandById(id);
//        brand.ifPresent(br -> {
//            br.setActive(true);
//            brandRepository.save(br);
//        });
//        return brand;
//    }
}
