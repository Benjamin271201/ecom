package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Brand;
import com.nashtech.ecommerce.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brand-management")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public Brand getBrandById(int id) {
        return brandService.findBrandById(id);
    }

    @GetMapping("/brands/all")
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping("/brands")
    public Brand addBrand(String name) {
        return brandService.addBrand(name);
    }

    @PutMapping("/brands")
    public Brand updateBrandName(int id, String newName) {
        return brandService.updateBrandName(id, newName);
    }

    @DeleteMapping("/brands")
    public Brand toggleBrandStatus(@RequestParam int id) {
        return brandService.updateBrandStatus(id);
    }
}
