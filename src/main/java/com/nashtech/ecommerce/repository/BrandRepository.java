package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    public Optional<Brand> findBrandByBrandName(String name);

    public Optional<Brand> findBrandById(int id);

    public List<Brand> findAll();

    public boolean existsByBrandName(String name);

    public boolean existsById(int id);
}
