package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Query("SELECT detail FROM CartDetail detail WHERE detail.cart.customer.id = :id")
    public List<CartDetail> getCartDetailsByCustomerId(@Param("id") int customerId);

    public void deleteAllByCartId(int cartId);
}
