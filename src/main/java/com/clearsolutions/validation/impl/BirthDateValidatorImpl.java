package com.clearsolutions.validation.impl;

import com.clearsolutions.exception.BirthDateParseException;
import com.clearsolutions.util.AppConstants;
import com.clearsolutions.validation.CustomBirthDayValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BirthDateValidatorImpl implements ConstraintValidator<CustomBirthDayValidator, String> {
    @Override
    public void initialize(CustomBirthDayValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String birthDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate dateOfBirth = LocalDate.MIN;
        try {
            dateOfBirth = LocalDate
                    .parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (BirthDateParseException exception) {
            log.error("Birth date validation failed! Params: {}", exception.getMessage());
            return false;
        }
        if (dateOfBirth.equals(LocalDate.MIN)
                || dateOfBirth.isAfter(AppConstants.maxAvailableBirthDate)
                || dateOfBirth.isBefore(AppConstants.minAvailableBirthDate)) {
            log.error("Check input parameters: date is incorrect!");
            return false;
        }
        return true;
    }
}
