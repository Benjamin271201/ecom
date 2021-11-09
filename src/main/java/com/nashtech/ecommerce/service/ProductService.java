package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.dto.ProductDTO;
import com.nashtech.ecommerce.dto.ProductInputDTO;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    //Errors
    private static final String PRODUCT_NOT_FOUND = "Product not found!";

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, BrandService brandService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    public List<ProductDTO> getProductsByCategoryId(int categoryId) {
        return productRepository.findProductsByCategoryId(categoryId).stream().map(ProductDTO::new).toList();
    }

    public List<ProductDTO> getProductsByBrandId(int brandId) {
        return productRepository.findProductsByBrandId(brandId).stream().map(ProductDTO::new).toList();
    }

    public List<ProductDTO> searchProductsByName(String keyword) {
        return productRepository.getProductByNameContainsIgnoreCase(keyword).stream().map(ProductDTO::new).toList();
    }

    public ProductInputDTO addProduct(ProductInputDTO newProduct) {
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setBrand(brandService.findBrandById(newProduct.getBrandId()));
        product.setCategory(categoryService.findCategoryById(newProduct.getCategoryId()));
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setStock(newProduct.getStock());
        product.setActive(true);
        return new ProductInputDTO(productRepository.save(product));
    }

    public Product getProductById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
    }

    public ProductDTO getProductDTOById(int id) {
        return new ProductDTO(getProductById(id));
    }

    public void updateProductStock(int productId, int newStock) {
        productRepository.updateProductStock(productId, newStock);
    }

    public void updateSold(int productId, int totalSoldNumber) {
        productRepository.updateProductSold(productId, totalSoldNumber);
    }
}
