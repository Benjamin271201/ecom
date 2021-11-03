package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.CustomerService;
import com.nashtech.ecommerce.service.LikedProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public List<ProductDTO> getLikedProducts(@PathVariable("id") int customerId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        return likedProductService.getLikedProducts(customerId);
    }

    //like-unlike a product
    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public void toggleLikedProduct(@RequestParam int customerId, @RequestParam int productId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        likedProductService.toggleLikedProduct(customerId, productId);
    }
}
