package com.example.springwebdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final Pattern UPPERCASE = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE = Pattern.compile(".*[a-z].*");
    private static final Pattern SPECIAL = Pattern.compile(".*[^a-zA-Z0-9].*");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Use @NotBlank separately if password is mandatory.
        if (password == null) return true;

        return password.length() >= 10
                && UPPERCASE.matcher(password).matches()
                && LOWERCASE.matcher(password).matches()
                && SPECIAL.matcher(password).matches();
    }
}
