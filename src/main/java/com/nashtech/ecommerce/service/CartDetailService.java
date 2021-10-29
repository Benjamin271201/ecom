package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.CartDTO;
import com.nashtech.ecommerce.dto.CartDetailDTO;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.CartDetailRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CartDetailService {
    //Errors
    private static final String PRODUCT_NOT_FOUND = "Product not found!";
    private static final String DETAIL_NOT_FOUND = "Cart detail not found!";

    private final CartDetailRepository cartDetailRepository;
    private final CartService cartService;
    private final ProductService productService;

    public CartDetailService(CartDetailRepository cartDetailRepository,
                             CartService cartService, ProductService productService) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    //customer add cartDetail -> add cart -> add cartDetail
    public CartDetailDTO addCartDetail(CartDetailDTO cartDetailDTO) {
        Product product = productService.getProductById(cartDetailDTO.getProductId());
        //if cart does not exists, create a new one
//        if (!cartService.existsByCartId(cartDetailDTO.getCartId())) {
//            Cart cart = cartService.createCart(cartDetailDTO.getCartId());
//        } else {
        Cart cart = cartService.getCartById(cartDetailDTO.getCartId());
        Optional<CartDetail> cartDetail = getCartDetailByCartIdAndProductId(cartDetailDTO);
        long total = cart.getTotal();
        long subTotal = cartDetailDTO.getQuantity() * product.getPrice();

        cart.setTotal(total + subTotal);
        cartService.updateCart(cart);
        //item alr exists in cart
        if (cartDetail.isPresent()) {
            int quantityOld = cartDetail.get().getQuantity();
            int quantityDiff = cartDetailDTO.getQuantity();
            int newQuantity = quantityOld + quantityDiff;
            cartDetail.get().setQuantity(newQuantity);
            cartDetail.get().setSubTotal();
            return new CartDetailDTO(cartDetailRepository.save(cartDetail.get()));
        }
        //if not, create new cart detail
        CartDetail newCart = new CartDetail();
        newCart.setQuantity(cartDetailDTO.getQuantity());
        newCart.setProduct(product);
        newCart.setCart(cart);
        newCart.setSubTotal();
        return new CartDetailDTO(cartDetailRepository.save(newCart));
    }

    //STILL WORKING ON THIS
//    public CartDetailDTO updateCartDetail(CartDetailDTO cartDetailDTO) {
//        //get cart detail in db
//        Product product = productService.getProductById(cartDetailDTO.getProductId());
//        Cart cart = cartService.getCartById(cartDetailDTO.getCartId());
//        Optional<CartDetail> cartDetail = getCartDetailByCartIdAndProductId(cartDetailDTO);
//        long total = cart.getTotal();
//        long subTotal = cartDetailDTO.getQuantity() * product.getPrice();
//        if (cartDetail.isEmpty()) {
//            throw new NotFoundException(DETAIL_NOT_FOUND);
//        }
//        //update quantity
//        int quantityNew = cartDetailDTO.getQuantity();
//        cartDetail.get().setQuantity(quantityNew);
//        cartDetail.get().setSubTotal();
//        //if new quantity < 1 -> remove
//        //if (quantityNew < 1) removeCartDetail(cartDetail.get().getId());
//        return new CartDetailDTO(cartDetailRepository.save(cartDetail.get()));
//    }

    public Optional<Product> getProductByCartId(int cartId) {
        return cartDetailRepository.getProductByCartId(cartId);
    }

    public Product getProductByCartDetailId(int id) {
        return cartDetailRepository
                .getProductByCartDetailId(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
    }

    public Optional<CartDetail> getCartDetailByCartIdAndProductId(CartDetailDTO cartDetailDTO) {
        int cartId = cartDetailDTO.getCartId();
        int productId = cartDetailDTO.getProductId();
        return cartDetailRepository.getCartDetailByCartIdAndProductId(cartId, productId);
    }

    public void removeCartDetail(int id) {
        cartDetailRepository.deleteById(id);
    }
}
