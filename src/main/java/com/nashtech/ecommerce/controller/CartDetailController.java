package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.CartDetailDTO;
import com.nashtech.ecommerce.service.CartDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-detail-management")
public class CartDetailController {
    private final CartDetailService cartDetailService;

    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/details/{id}/product")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public Product getProductFromCartDetailId(@PathVariable("id") int id) {
        return cartDetailService.getProductByCartDetailId(id);
    }

    //for add/update
    @PostMapping("/details")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public CartDetailDTO addCartDetail(@RequestBody CartDetailDTO cartDetailDTO) {
        return cartDetailService.addCartDetail(cartDetailDTO);
    }

    //remove individual cart detail
    @DeleteMapping("/details")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public void removeCartDetail(@RequestParam int id){
        cartDetailService.removeCartDetail(id);
    }
}
