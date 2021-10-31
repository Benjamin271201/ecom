package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.service.ProductService;
import com.nashtech.ecommerce.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final TransactionDetailService transactionDetailService;

    public ProductController(ProductService productService, TransactionDetailService transactionDetailService) {
        this.productService = productService;
        this.transactionDetailService = transactionDetailService;
    }

    //TODO: add purchased
    @GetMapping("/product/all")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
//        return productService.getAllProducts().stream().map(
//                productDTO -> productDTO.setPurchased(transactionDetailService.getPurchasedByProductId(productDTO.getId()))
//        ).toList();
    }

    @GetMapping("/product/search/{keyword}")
    public List<ProductDTO> searchProductsByName(@PathVariable("keyword") String keyword) {
        return productService.searchProductsByName(keyword);
    }

    @PostMapping("/product/add")
    public ProductDTO addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

}
