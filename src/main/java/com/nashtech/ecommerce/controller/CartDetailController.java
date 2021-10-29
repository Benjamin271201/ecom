package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.CartDetailDTO;
import com.nashtech.ecommerce.service.CartDetailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-detail-management")
public class CartDetailController {
    private final CartDetailService cartDetailService;

    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/details")
    public Product getProductFromCartDetailId(@RequestBody int id) {
        return cartDetailService.getProductByCartDetailId(id);
    }

    //for add/update
    @PostMapping("/details")
    public CartDetailDTO addCartDetail(@RequestBody CartDetailDTO cartDetailDTO) {
        return cartDetailService.addCartDetail(cartDetailDTO);
    }

//    @PutMapping("/details")
//    public CartDetailDTO updateCartDetail(@RequestBody CartDetailDTO cartDetailDTO) {
//        return cartDetailService.updateCartDetail(cartDetailDTO);
//    }

    //remove individual cart detail
    @DeleteMapping("/details")
    public void removeCartDetail(@RequestParam int id){
        cartDetailService.removeCartDetail(id);
    }
}
