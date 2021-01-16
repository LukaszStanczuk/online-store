package com.onlinestore.annotation;

import com.onlinestore.validation.ExistingCategoryValidator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ExistingCategoryValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingCategory {

    String message() default  "Category doesn't exist";

    Class<?>[] groups() default {};

    Class <? extends Payload> [] payload() default {};
}
