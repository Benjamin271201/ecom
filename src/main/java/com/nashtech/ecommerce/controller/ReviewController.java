package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.dto.ReviewDTO;
import com.nashtech.ecommerce.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review-management")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //TODO: get a specific amount of reviews for each pages

    //get all reviews of a product
    @GetMapping
    public List<ReviewDTO> getAllReviewsByProductId(@RequestParam int productId) {
        return reviewService.getAllReviewsByProductId(productId);
    }

    //add review
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ReviewDTO addReview(@RequestParam int productId, @RequestParam int customerId,
                               @RequestParam String context, @RequestParam int rating) {
        return reviewService.addReview(productId, customerId, context, rating);
    }

    //update review
    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ReviewDTO editReview(@RequestParam int id, @RequestParam String context, @RequestParam int rating) {
        return reviewService.editReview(id, context, rating);
    }

    //delete review
    //change status of isActive to false
    @DeleteMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public void deleteReview(@RequestParam int id) {
        reviewService.deleteReview(id);
    }

}
