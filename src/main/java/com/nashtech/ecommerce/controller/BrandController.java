package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Brand;
import com.nashtech.ecommerce.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<Brand> addBrand(Brand brand) {
        return ResponseEntity.ok(brandService.addBrand(brand));
    }
}
