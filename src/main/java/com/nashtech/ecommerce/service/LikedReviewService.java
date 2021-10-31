package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.LikedReview;
import com.nashtech.ecommerce.repository.LikedReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikedReviewService {
    private final LikedReviewRepository likedReviewRepository;
    private final ReviewService reviewService;
    private final CustomerService customerService;

    public LikedReviewService(LikedReviewRepository likedReviewRepository, ReviewService reviewService,
                              CustomerService customerService) {
        this.likedReviewRepository = likedReviewRepository;
        this.reviewService = reviewService;
        this.customerService = customerService;
    }

    //add liked review
    public LikedReview addLikedReview(int reviewId, int customerId) {
        LikedReview likedReview = new LikedReview();
        likedReview.setReview(reviewService.getReviewById(reviewId));
        likedReview.setCustomer(customerService.getCustomerById(customerId));
        return likedReviewRepository.save(likedReview);
    }

    //like-unlike other reviews
    public void toggleReviewLikeStatus(int reviewId, int customerId) {
        //check in liked review map
        Optional<LikedReview> likedReview = likedReviewRepository
                .findLikedReviewByCustomerIdAndReviewId(customerId, reviewId);
        likedReview.ifPresentOrElse(
                likedReviewRepository::delete,
                () -> addLikedReview(reviewId, customerId)
        );
    }
}
