package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.CartDetail;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public @Data
class CartDetailDTO {
    private int id;
    @NotNull (message = "Cart id cannot be empty!")
    private int cartId;
    @NotNull (message = "Product id cannot be empty!")
    private int productId;
    private String productName;
    private long productPrice;
    @NotNull (message = "Quantity cannot be empty!")
    private int quantity;

    public CartDetailDTO(CartDetail cartDetail) {
        this.id = cartDetail.getId();
        this.cartId = cartDetail.getCart().getId();
        this.productId = cartDetail.getProduct().getId();
        this.productName = cartDetail.getProduct().getName();
        this.productPrice = cartDetail.getProduct().getPrice();
        this.quantity = cartDetail.getQuantity();
    }
}
