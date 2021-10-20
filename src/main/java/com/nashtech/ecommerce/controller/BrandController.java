package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Brand;
import com.nashtech.ecommerce.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PostMapping
    public ResponseEntity<Brand> addBrand(Brand brand) {
        return ResponseEntity.ok(brandService.addBrand(brand));
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<Brand>> updateBrandName(int id, String newName) {
        return ResponseEntity.ok(brandService.updateBrandName(id, newName));
    }

    @PutMapping("/disable")
    public ResponseEntity<Optional<Brand>> disableBrand(@RequestParam int id) {
        return ResponseEntity.ok(brandService.disableBrand(id));
    }

    @PutMapping("/enable")
    public ResponseEntity<Optional<Brand>> enableBrand(@RequestParam int id) {
        return ResponseEntity.ok(brandService.enableBrand(id));
    }
}
