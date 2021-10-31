package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Review;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public @Data class ReviewDTO {
    //TODO: add constraints
    private int id;
    private String customerUsername;
    private Date postedDate;
    private Date modifiedDate;
    private String context;
    private int rating;
    private int likes;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.customerUsername = review.getCustomer().getAccount().getUsername();
        this.postedDate = review.getPostedDate();
        this.modifiedDate = review.getModifiedDate();
        this.context = review.getContext();
        this.rating = review.getRating();
        this.likes = review.getLikes().size();
    }
}
