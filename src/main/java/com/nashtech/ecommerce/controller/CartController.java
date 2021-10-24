package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.service.CartDetailService;
import com.nashtech.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;
    private final CartDetailService cartDetailService;

    public CartController(CartService cartService, CartDetailService cartDetailService) {
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/cart/uid")
    public List<CartDetail> getCartDetailsByCustomerId(@RequestParam int customerId) {
        return cartService.getCartDetailsByCustomerId(customerId);
    }

    @PostMapping("/cart/add")
    public Cart createCart(@RequestParam int customerId) {
        return cartService.createCart(customerId);
    }

    @DeleteMapping("/cart/uid")
    public void deleteCartByCustomerId(@RequestParam int customerId) {
        cartService.deleteCart(customerId);
    }

}
