package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    //TODO: add ignore case on all search
    public List<Product> getProductByNameContainsIgnoreCase(String keyword);

    public List<Product> findProductsByCategoryId(int categoryId);

    public List<Product> findProductsByBrandId(int brandId);

    @Modifying
    @Query ("UPDATE Product p SET p.stock = :newStock WHERE p.id = :id")
    public void updateProductStock(@Param("id") int id, @Param("newStock") int newStock);

    @Modifying
    @Query ("UPDATE Product p SET p.sold = :sold WHERE p.id = :id")
    public void updateProductSold(@Param("id") int id, @Param("sold") int totalSoldNumber);
}
