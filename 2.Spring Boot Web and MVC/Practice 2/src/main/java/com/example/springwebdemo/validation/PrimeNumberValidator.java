package com.example.springwebdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumber, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // Standard Bean Validation convention: null is valid; use @NotNull when required.
        if (value == null) return true;
        if (value < 2) return false;
        if (value == 2) return true;
        if (value % 2 == 0) return false;

        for (int divisor = 3; divisor <= value / divisor; divisor += 2) {
            if (value % divisor == 0) return false;
        }
        return true;
    }
}
