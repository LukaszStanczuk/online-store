package com.onlinestore.product;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.category.Category;

public interface ProductRepository extends JpaRepository<Product,Long> {

  Page<Product> findByCategory(Category category, Pageable pageable);
}
