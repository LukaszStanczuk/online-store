package com.onlinestore.product;

import com.onlinestore.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(Category category, Pageable pageable);

    @Query("SELECT p FROM Product p left join p.category c WHERE c.name = :categoryName")
    Page<Product> findByCategory(@Param("categoryName") String categoryName, Pageable pageable);
}
