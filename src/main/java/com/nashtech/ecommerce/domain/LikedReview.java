package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "liked_review", uniqueConstraints={@UniqueConstraint(columnNames = {"customer_id" , "review_id"})})

public @Data class LikedReview implements Serializable {
    @Id
    private int id;

    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne @JoinColumn (name = "review_id")
    private Review review;
}
