package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.dto.CartDTO;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.CartDetailRepository;
import com.nashtech.ecommerce.repository.CartRepository;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {
    //Error
    private static final String CART_NOT_FOUND = "Cart not found!";
    private static final String CUSTOMER_NOT_FOUND = "Customer not found!";

    //Constructor
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final CustomerRepository customerRepository;
    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository
            , CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.customerRepository = customerRepository;
    }

    public Cart getCartById(int id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CART_NOT_FOUND));
    }

    public CartDTO getCartByCustomerId(int customerId) {
        Cart cart = cartRepository
                .findByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException(CART_NOT_FOUND));
        return new CartDTO(cart);
    }

    public List<CartDetail> getCartDetailsByCustomerId(int customerId) {
        return cartDetailRepository.getCartDetailsByCustomerId(customerId);
    }

    public Cart createCart(int customerId) {
        Cart cart = new Cart(customerRepository
                .findById(customerId)
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND)));
        return cartRepository.save(cart);
    }

    public Cart updateCart(Cart cart) {
        cartRepository
                .findById(cart.getId())
                .orElseThrow(() -> new NotFoundException(CART_NOT_FOUND));
        return cartRepository.save(cart);
    }

    @Transactional
    public void deleteCartByCustomerId(int customerId) {
        cartRepository.deleteCartByCustomerId(customerId);
    }
}
