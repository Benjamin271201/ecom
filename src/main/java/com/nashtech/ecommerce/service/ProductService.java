package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    //Errors
    private static final String PRODUCT_NOT_FOUND = "Product not found!";

    private final ProductRepository productRepository;
    private final TransactionDetailService transactionDetailService;

    public ProductService(ProductRepository productRepository, TransactionDetailService transactionDetailService) {
        this.productRepository = productRepository;
        this.transactionDetailService = transactionDetailService;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    public List<ProductDTO> searchProductsByName(String keyword) {
        return productRepository.getProductByNameContains(keyword).stream().map(ProductDTO::new).toList();
    }

    //TODO: create a product form to limit the fields needed
    public ProductDTO addProduct(Product product) {
        return new ProductDTO(productRepository.save(product));
    }

    public Product getProductById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
    }

    public void updateProductStock(int productId, int newStock) {
        productRepository.updateProductStock(productId, newStock);
    }

    public void updateSold(int productId, int totalSoldNumber) {
        productRepository.updateProductSold(productId, totalSoldNumber);
    }
}
