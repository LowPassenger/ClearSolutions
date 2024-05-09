package com.clearsolutions.validation;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "^\\+[0-9]{12}+$",
        message = "Phone number must starts with + and has exactly 12 digits before")
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface CustomPhoneNumberValidator {
}
