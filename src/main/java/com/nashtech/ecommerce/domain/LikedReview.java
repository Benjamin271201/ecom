package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "liked_review")

public @Data class LikedReview {
    @Id
    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne @JoinColumn (name = "review_id")
    private Review review;
}
