package com.clearsolutions.exception.exceptionhandling;

import com.clearsolutions.exception.BirthDateParseException;
import com.clearsolutions.exception.ExceptionBody;
import com.clearsolutions.exception.ResourceNotFoundException;
import com.clearsolutions.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandling {
    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleValidationFailed(ValidationFailedException exception) {
        return new ExceptionBody(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(final ResourceNotFoundException exception) {
        ExceptionBody eBody = new ExceptionBody(exception.getMessage());
        return eBody;
    }

    @ExceptionHandler(BirthDateParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBirthdateParsingFailed(ValidationFailedException exception) {
        return new ExceptionBody(exception.getMessage());
    }
}
