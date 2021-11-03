package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.dto.CartDTO;
import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.AccountService;
import com.nashtech.ecommerce.service.CartDetailService;
import com.nashtech.ecommerce.service.CartService;
import com.nashtech.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart-management")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    private CustomerService customerService;

    @GetMapping("/carts/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public CartDTO getCartByCustomerId(@PathVariable("id") int customerId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        return cartService.getCartByCustomerId(customerId);
    }

    @DeleteMapping("/carts/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public void deleteCartByCustomerId(@PathVariable("id") int customerId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        cartService.deleteCartByCustomerId(customerId);
    }
}
