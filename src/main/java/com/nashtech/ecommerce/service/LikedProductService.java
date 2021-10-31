package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.LikedProduct;
import com.nashtech.ecommerce.domain.LikedReview;
import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.repository.LikedProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedProductService {
    private final LikedProductRepository likedProductRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public LikedProductService(LikedProductRepository productRepository,
                               ProductService productService, CustomerService customerService) {
        this.likedProductRepository = productRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    //get liked products by customers
    public List<ProductDTO> getLikedProducts(int customerId) {
        return likedProductRepository.getLikedProductsByCustomerId(customerId).stream().map(ProductDTO::new).toList();
    }

    //toggle like/unlike
    public void toggleLikedProduct(int customerId, int productId) {
        //check in liked product map
        Optional<LikedProduct> productLike = likedProductRepository
                .findLikedProductByCustomerIdAndProductId(customerId, productId);
        productLike.ifPresentOrElse(
                likedProductRepository::delete,
                () -> addLikedProduct(productId, customerId)
        );
    }

    //add product like
    public LikedProduct addLikedProduct(int productId, int customerId) {
        LikedProduct likedProduct = new LikedProduct();
        likedProduct.setProduct(productService.getProductById(productId));
        likedProduct.setCustomer(customerService.getCustomerById(customerId));
        return likedProductRepository.save(likedProduct);
    }
}
