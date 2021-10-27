package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.dto.CartDTO;
import com.nashtech.ecommerce.service.CartDetailService;
import com.nashtech.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart-management")
public class CartController {
    private final CartService cartService;
    private final CartDetailService cartDetailService;

    public CartController(CartService cartService, CartDetailService cartDetailService) {
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
    }

//    @GetMapping("/carts")
//    public List<CartDetail> getCartDetailsByCustomerId(@RequestParam int customerId) {
//        return cartService.getCartDetailsByCustomerId(customerId);
//    }

    @GetMapping("/carts")
    public CartDTO getCartByCustomerId(@RequestParam int customerId) {
        return cartService.getCartByCustomerId(customerId);
    }

    @PostMapping("/carts")
    public Cart createCart(@RequestParam int customerId) {
        return cartService.createCart(customerId);
    }

    @DeleteMapping("/carts")
    public void deleteCartByCustomerId(@RequestParam int customerId) {
        cartService.deleteCartByCustomerId(customerId);
    }
}
