package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.service.LikedProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-management/likes")
public class LikedProductController {
    private final LikedProductService likedProductService;

    public LikedProductController(LikedProductService likedProductService) {
        this.likedProductService = likedProductService;
    }

    @GetMapping("/customers")
    public List<ProductDTO> getLikedProducts(@RequestParam int customerId) {
        return likedProductService.getLikedProducts(customerId);
    }

    @PostMapping
    public void toggleLikedProduct(@RequestParam int customerId, @RequestParam int productId) {
        likedProductService.toggleLikedProduct(customerId, productId);
    }
}
