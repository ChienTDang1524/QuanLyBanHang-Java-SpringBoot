package org.example.test01.repository;

import org.example.test01.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    // Có thể thêm các custom query tại đây nếu cần
}