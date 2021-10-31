package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public List<Review> getAllByProductId(int productId);

    @Modifying
    @Query("UPDATE Review r SET r.isActive = false WHERE r.id = :id")
    public void deactivateReview(int id);
}
