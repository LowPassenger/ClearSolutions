package com.clearsolutions.validation.impl;

import com.clearsolutions.exception.BirthDateParseException;
import com.clearsolutions.exception.ValidationFailedException;
import com.clearsolutions.util.AppConstants;
import com.clearsolutions.validation.CustomBirthDayValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BirthDateValidatorImpl implements
        ConstraintValidator<CustomBirthDayValidator, String> {
    @Override
    public void initialize(CustomBirthDayValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String birthDate,
                           ConstraintValidatorContext constraintValidatorContext) {
        LocalDate dateOfBirth = LocalDate.MIN;
        try {
            dateOfBirth = LocalDate
                    .parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (BirthDateParseException exception) {
            log.error("Birth date validation failed! Params: {}", exception.getMessage());
            throw new ValidationFailedException(exception.getMessage());
        }
        if (dateOfBirth.equals(LocalDate.MIN)
                || dateOfBirth.equals(LocalDate.now())
                || dateOfBirth.isAfter(LocalDate.now())
                || dateOfBirth.isAfter(AppConstants.maxAvailableBirthDate)
                || dateOfBirth.isBefore(AppConstants.minAvailableBirthDate)) {
            log.error("Check input parameters: date is incorrect!");
            return false;
        }
        return true;
    }
}
