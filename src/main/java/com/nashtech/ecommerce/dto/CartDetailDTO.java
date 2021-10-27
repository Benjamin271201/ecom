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
    @NotNull (message = "Cart id cannot be empty!")
    private int cartId;
    @NotNull (message = "Product id cannot be empty!")
    private int productId;
    @NotNull (message = "Quantity cannot be empty!")
    private int quantity;

    public CartDetailDTO(CartDetail cartDetail) {
        this.cartId = cartDetail.getCart().getId();
        this.productId = cartDetail.getProduct().getId();
        this.quantity = cartDetail.getQuantity();
    }
}
