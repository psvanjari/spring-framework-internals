package com.example.springwebdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PrimeNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimeNumber {
    String message() default "must be a prime number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
