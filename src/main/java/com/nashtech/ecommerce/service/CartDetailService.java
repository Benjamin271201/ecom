package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.CartDetailDTO;
import com.nashtech.ecommerce.repository.CartDetailRepository;
import com.nashtech.ecommerce.service.CartService;
import com.nashtech.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailService {
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
        Cart cart = cartService.getCartById(cartDetailDTO.getCartId());
        long subTotal = cartDetailDTO.getQuantity() * product.getPrice();
        Optional<CartDetail> cartDetail =
                cartDetailRepository
                        .getCartDetailByCartIdAndProductId(cartDetailDTO.getCartId(), cartDetailDTO.getProductId());
        cart.setTotal(cart.getTotal() + subTotal);
        cartService.updateCart(cart);
        //item alr exists in cart
        if (cartDetail.isPresent()) {
            cartDetail.get().setQuantity(cartDetail.get().getQuantity()+ cartDetailDTO.getQuantity());
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
    public Optional<Product> getProductByCartId(int cartId) {
        return cartDetailRepository.getProductByCartId(cartId);
    }
}
