package com.clearsolutions.validation.impl;

import com.clearsolutions.validation.CustomBirthDayValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthDateValidatorImpl implements ConstraintValidator<CustomBirthDayValidator, String> {
    @Override
    public void initialize(CustomBirthDayValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String birthDate, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
