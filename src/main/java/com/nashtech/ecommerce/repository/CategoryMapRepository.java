package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.CategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapRepository extends JpaRepository<CategoryMap, Integer> {
}
