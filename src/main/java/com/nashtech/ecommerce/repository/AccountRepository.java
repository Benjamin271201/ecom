package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Modifying
    @Query ("UPDATE Account a SET a.isBanned = true WHERE a.id = :id")
    public void deactivateAccount(@Param("id") int id);

    public boolean existsAccountByUsername (String username);

    public Optional<Account> findAccountByUsername (String username);

    @Modifying
    @Query ("UPDATE Account a SET a.password = :newPassword WHERE a.id = :id")
    public void updatePassword(@Param("id") int id, @Param("newPassword") String newPassword);
}
