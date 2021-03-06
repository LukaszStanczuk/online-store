package com.onlinestore.validation;

import com.onlinestore.annotation.ExistingProduct;
import com.onlinestore.exception.NotFoundException;
import com.onlinestore.product.Product;
import com.onlinestore.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistingProductValidator implements ConstraintValidator<ExistingProduct, Long> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        if (productId == null) {
            return false;
        }
        return productWithIdExist(productId);
    }

    public boolean productWithIdExist(Long id) {
        return findOptionalProductById(id).isPresent();
    }

    public Optional<Product> findOptionalProductById(Long id) {
        return Optional.of(productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with " + id + "not exist ")));
    }
}

