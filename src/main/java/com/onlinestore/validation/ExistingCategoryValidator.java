package com.onlinestore.validation;

import com.onlinestore.annotation.ExistingCategory;

import com.onlinestore.category.Category;
import com.onlinestore.category.CategoryRepository;
import com.onlinestore.exception.NotFoundException;
import com.onlinestore.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class ExistingCategoryValidator implements ConstraintValidator<ExistingCategory, Long> {



        private final CategoryRepository categoryRepository;

        @Override
        public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
            if (productId == null) {
                return false;
            }
            return productWithIdExist(productId);
        }

        public boolean productWithIdExist(Long id) {
            return findOptionalCategoryById(id).isPresent();
        }

        public Optional<Category> findOptionalCategoryById(Long id) {
            return Optional.of(categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with " + id + "not exist ")));
        }
    }

