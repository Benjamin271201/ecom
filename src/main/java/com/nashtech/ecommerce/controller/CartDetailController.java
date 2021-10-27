package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.dto.CartDetailDTO;
import com.nashtech.ecommerce.service.CartDetailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-detail-management")
public class CartDetailController {
    private CartDetailService cartDetailService;

    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @PostMapping("/cart-details")
    public CartDetailDTO addCartDetail(@RequestBody CartDetailDTO cartDetailDTO) {
        return cartDetailService.addCartDetail(cartDetailDTO);
    }

    //update cart details

    @DeleteMapping("/cart-details")
    public void removeCartDetail(@RequestParam int cartId){

    }



//    @PutMapping("/cart-details")
//    public void removeAllCartDetailsByCustomerId(@RequestParam int customerId){
//
//    }
}
