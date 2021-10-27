package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.CartDetail;
import com.nashtech.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Query("SELECT detail FROM CartDetail detail WHERE detail.cart.customer.id = :id")
    public List<CartDetail> getCartDetailsByCustomerId(@Param("id") int customerId);

    @Query("SELECT detail.product FROM CartDetail detail WHERE detail.cart.id = :id")
    public Optional<Product> getProductByCartId(@Param("id") int cartId);

    public Optional<CartDetail> getCartDetailByCartIdAndProductId(int cartId, int productId);

    public void deleteAllByCartId(int cartId);
}
