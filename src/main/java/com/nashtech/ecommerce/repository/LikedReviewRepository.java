package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.LikedReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedReviewRepository extends JpaRepository<LikedReview, Integer> {
}
