package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.LikedReview;
import com.nashtech.ecommerce.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikedReviewRepository extends JpaRepository<LikedReview, Integer> {
    public Optional<LikedReview> findLikedReviewByCustomerIdAndReviewId(int customerId, int reviewId);
}
