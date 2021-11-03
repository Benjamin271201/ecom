package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.CustomerService;
import com.nashtech.ecommerce.service.LikedReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review-management/likes")
public class LikedReviewController {
    private final LikedReviewService likedReviewService;

    public LikedReviewController(LikedReviewService likedReviewService) {
        this.likedReviewService = likedReviewService;
    }

    @Autowired
    private CustomerService customerService;

    //TODO: test this
    //like-unlike a review
    @PostMapping()
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void toggleReviewLikeStatus(@PathVariable("id") int customerId, @RequestParam int reviewId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        likedReviewService.toggleReviewLikeStatus(customerId, reviewId);
    }
}
