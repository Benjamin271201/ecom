package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.dto.ReviewDTO;
import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.CustomerService;
import com.nashtech.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerService customerService;

    //TODO: get a specific amount of reviews for each page

    //get all reviews of a product
    @GetMapping
    public List<ReviewDTO> getAllReviewsByProductId(@RequestParam int productId) {
        return reviewService.getAllReviewsByProductId(productId);
    }

    //add review
    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ReviewDTO addReview(@RequestParam int productId, @RequestParam int customerId,
                               @RequestParam String context, @RequestParam int rating) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        return reviewService.addReview(productId, customerId, context, rating);
    }

    //update review
    @PutMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ReviewDTO editReview(@RequestParam int id, @RequestParam String context, @RequestParam int rating) {
        SecurityUtils.isForbidden(reviewService.getReviewById(id).getCustomer().getAccount().getId());
        return reviewService.editReview(id, context, rating);
    }

    //delete review
    //change status of isActive to false
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public void deleteReview(@RequestParam int id) {
        SecurityUtils.isForbidden(reviewService.getReviewById(id).getCustomer().getAccount().getId());
        reviewService.deleteReview(id);
    }

}
