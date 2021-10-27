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

    @GetMapping("/brands/all")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PostMapping("/brands")
    public ResponseEntity<Brand> addBrand(Brand brand) {
        return ResponseEntity.ok(brandService.addBrand(brand));
    }

    @PutMapping("/brands")
    public ResponseEntity<Optional<Brand>> updateBrandName(int id, String newName) {
        return ResponseEntity.ok(brandService.updateBrandName(id, newName));
    }

    @DeleteMapping("/brands")
    public ResponseEntity<Optional<Brand>> toggleBrandStatus(@RequestParam int id) {
        return ResponseEntity.ok(brandService.toggleBrandStatus(id));
    }

//    @PutMapping("/brand/enable")
//    public ResponseEntity<Optional<Brand>> enableBrand(@RequestParam int id) {
//        return ResponseEntity.ok(brandService.enableBrand(id));
//    }
}
