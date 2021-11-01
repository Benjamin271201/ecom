package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.service.LikedReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review-management/likes")
public class LikedReviewController {
    private final LikedReviewService likedReviewService;

    public LikedReviewController(LikedReviewService likedReviewService) {
        this.likedReviewService = likedReviewService;
    }

    //TODO: test this
    //like-unlike a review
    @PostMapping()
    @PreAuthorize("hasRole('CUSTOMER')")
    public void toggleReviewLikeStatus(@PathVariable("id") int customerId, @RequestParam int reviewId) {
        likedReviewService.toggleReviewLikeStatus(customerId, reviewId);
    }
}
