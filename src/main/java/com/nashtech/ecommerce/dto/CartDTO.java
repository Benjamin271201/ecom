package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Cart;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public @Data class CartDTO {
    //TODO: set constraints
    private int customerId;
    private Set<CartDetailDTO> details;
    private long total;

    public CartDTO (Cart cart) {
        this.customerId = cart.getCustomer().getId();
        this.total = cart.getTotal();
        this.details = new HashSet<>();
        cart.getDetails().forEach(cartDetail -> {
            CartDetailDTO cartDetailDTO = new CartDetailDTO(cartDetail);
            this.details.add(cartDetailDTO);
        });
    }
}
