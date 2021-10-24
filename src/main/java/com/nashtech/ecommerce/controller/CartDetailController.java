package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.service.CartDetailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartDetailController {
    private CartDetailService cartDetailService;

    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @PostMapping("/cart/detail/add")
    public CartDetail addCartDetail(@RequestParam int cartId, @RequestBody CartDetail cartDetail) {
        return cartDetailService.addCartDetail(cartId, cartDetail);
    }
}
