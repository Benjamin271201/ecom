package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.repository.CartDetailRepository;
import com.nashtech.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository, CartRepository cartRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
    }

    //customer add detail -> add cart -> add detail
    public CartDetail addCartDetail(int cartId, CartDetail detail) {
        Cart cart = cartRepository.getById(cartId);
        cart.getDetails().add(detail);
        cart.setTotal(cart.getTotal() + detail.getSubTotal());
        cartRepository.save(cart);
        return cartDetailRepository.save(detail);
    }
}
