package com.onlinestore.validation;

import com.onlinestore.annotation.ExistingProduct;
import com.onlinestore.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistingProductValidator implements ConstraintValidator<ExistingProduct,Long> {
    private final ProductService productService;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        if (!(productId instanceof Long)) {
            return false;
        }
        if (productId == null) {
            return false;
        }
        return productService.productWithIdExist(productId);
    }
}
