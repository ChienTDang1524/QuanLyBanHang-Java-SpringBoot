// BrandRepository.java
package org.example.test01.repository;

import org.example.test01.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT DISTINCT b.brandName FROM Brand b")
    List<String> findAllBrandNames();
}