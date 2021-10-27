package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Category;
import com.nashtech.ecommerce.domain.CategoryMap;
import com.nashtech.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapRepository extends JpaRepository<CategoryMap, Integer> {

    @Query("SELECT map.category FROM CategoryMap map WHERE map.product.id = :productId")
    public List<Category> getCategoriesByProductId(@Param("productId") int productId);

    @Query("SELECT map.product FROM CategoryMap map WHERE map.category.id = :categoryId")
    public List<Product> getProductsByCategoryId(@Param("categoryId") int categoryId);
}
