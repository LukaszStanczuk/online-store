package com.onlinestore.annotation;



import com.onlinestore.validation.ExistingProductValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistingProductValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingProduct {

    String message() default "Product doesn't not exist";

    Class<?>[] groups() default {};

    Class <? extends Payload> [] payload() default {};
}
