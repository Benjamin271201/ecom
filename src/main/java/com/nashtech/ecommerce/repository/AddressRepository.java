package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    public List<Address> getAddressesByCustomerId(int customerId);

    @Modifying
    @Query ("UPDATE Address a SET a.isActive = false WHERE a.id = :id")
    public void deactivateAddress(@Param("id") int id);
}
