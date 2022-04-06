package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Review;
import com.nashtech.ecommerce.dto.ReviewDTO;
import com.nashtech.ecommerce.exception.AlreadyExistsException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.LikedReviewRepository;
import com.nashtech.ecommerce.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    //Error msg
    private static final String REVIEW_NOT_FOUND = "Review not found!";
    private static final String REVIEW_ALREADY_EXISTS = "Review for this product has already exists!";

    private final ReviewRepository reviewRepository;
    private final LikedReviewRepository likedReviewRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public ReviewService(ReviewRepository reviewRepository, LikedReviewRepository likedReviewRepository,
                         ProductService productService, CustomerService customerService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.likedReviewRepository = likedReviewRepository;
    }

    //get all reviews by product id
    public List<ReviewDTO> getAllReviewsByProductId(int productId) {
        List<ReviewDTO> listReviewDTO = new ArrayList<>();
        reviewRepository
                .getAllByProductId(productId)
                .forEach(review -> listReviewDTO.add(new ReviewDTO(review)));
        return listReviewDTO;
    }

    //get review by id
    public Review getReviewById(int id) {
        return reviewRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));
    }

    //TODO: if reviewed -> not allowed to review again (check isActive as well)
    //TODO: only customer who bought this product has the ability to review
    //add review
    public ReviewDTO addReview(int productId, int customerId, String context, int rating) {
        Review review = new Review();
        if (reviewRepository.existsByCustomerIdAndProductId(customerId, productId))
            throw new AlreadyExistsException("review", REVIEW_ALREADY_EXISTS);
        review.setProduct(productService.getProductById(productId));
        review.setCustomer(customerService.getCustomerById(customerId));
        review.setPostedDate();
        review.setContext(context);
        review.setRating(rating);
        review.setActive(true);
        return new ReviewDTO(reviewRepository.save(review));
    }

    //edit review
    public ReviewDTO editReview(int id, String context, int rating) {
        Review review = getReviewById(id);
        review.setContext(context);
        review.setRating(rating);
        review.setModifiedDate(Date.valueOf(LocalDate.now()));
        return new ReviewDTO(reviewRepository.save(review));
    }

    //delete review
    public void deleteReview(int id) {
        reviewRepository.deactivateReview(id);
    }
}
