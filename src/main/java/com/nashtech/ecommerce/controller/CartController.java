package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.dto.CartDTO;
import com.nashtech.ecommerce.service.CartDetailService;
import com.nashtech.ecommerce.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart-management")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public CartDTO getCartByCustomerId(@PathVariable("id") int customerId) {
        return cartService.getCartByCustomerId(customerId);
    }

    //TODO: admin only? might remove this as well since addCartDetail can create a cart instead
    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public Cart createCart(@RequestParam int customerId) {
        return cartService.createCart(customerId);
    }

    @DeleteMapping("/carts/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public void deleteCartByCustomerId(@PathVariable("id") int customerId) {
        cartService.deleteCartByCustomerId(customerId);
    }
}
