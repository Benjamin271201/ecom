package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/search/{keyword}")
    public ResponseEntity<List<Product>> searchProductsByName(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok(productService.searchProductsByName(keyword));
    }

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

}
