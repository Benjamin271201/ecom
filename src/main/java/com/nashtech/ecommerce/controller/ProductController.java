package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.dto.ProductInputDTO;
import com.nashtech.ecommerce.service.ProductService;
import com.nashtech.ecommerce.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product-management")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/categories/{id}")
    public List<ProductDTO> getProductsByCategoryId(@PathVariable("id") int categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/products/brands/{id}")
    public List<ProductDTO> getProductsByBrandId(@PathVariable("id") int brandId) {
        return productService.getProductsByBrandId(brandId);
    }

    @GetMapping(value = "/products", params="id")
    public ProductDTO getProductById(@RequestParam int id) {
        return productService.getProductDTOById(id);
    }

    @GetMapping(value = "/products", params = "keyword")
    public List<ProductDTO> searchProductsByName(@RequestParam String keyword) {
        return productService.searchProductsByName(keyword);
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductInputDTO addProduct(@Valid @RequestBody ProductInputDTO product) {
        return productService.addProduct(product);
    }
}
