package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.service.LikedProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-management/likes")
public class LikedProductController {
    private final LikedProductService likedProductService;

    public LikedProductController(LikedProductService likedProductService) {
        this.likedProductService = likedProductService;
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public List<ProductDTO> getLikedProducts(@PathVariable("id") int customerId) {
        return likedProductService.getLikedProducts(customerId);
    }

    //like-unlike a product
    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public void toggleLikedProduct(@RequestParam int customerId, @RequestParam int productId) {
        likedProductService.toggleLikedProduct(customerId, productId);
    }
}
