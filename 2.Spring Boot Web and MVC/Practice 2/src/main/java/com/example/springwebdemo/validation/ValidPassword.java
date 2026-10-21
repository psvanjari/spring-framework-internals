package com.example.springwebdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "password must be at least 10 characters and contain uppercase, lowercase, and a special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
