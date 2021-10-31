package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public @Data class ProductDTO {
    //TODO: add constraints
    private int id;
    private String name;
    private String brandName;
    private String categoryName;
    private long price;
    private float rating;
    private String description;
    private int stock;
    private int sold;
    private Set<ReviewDTO> reviews;
    private boolean isActive;
    private int likes;
    private int purchased;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brandName = product.getBrand().getBrandName();
        this.categoryName = product.getCategory().getCategoryName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.sold = product.getSold();
        this.reviews = new HashSet<>();
        this.isActive = product.isActive();
        this.rating = 0;
        this.purchased = 0;
        this.likes = product.getLikes().size();

        product.getReviews().forEach(review -> {
            ReviewDTO reviewDTO = new ReviewDTO(review);
            this.rating += reviewDTO.getRating();
            this.reviews.add(reviewDTO);
        });
        //calculate avg ratings
        int size = product.getReviews().size();
        if (size > 0) {
            this.rating = this.rating / size;
        }
    }
}
