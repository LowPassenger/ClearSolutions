package com.clearsolutions.exception.exceptionhandling;

import com.clearsolutions.exception.BirthDateParseException;
import com.clearsolutions.exception.ExceptionBody;
import com.clearsolutions.exception.ResourceNotFoundException;
import com.clearsolutions.exception.ValidationFailedException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandling {
    private int status;
    private Map<String, String> errors = new HashMap<>();

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleValidationFailed(ValidationFailedException exception) {
        status = HttpStatus.BAD_REQUEST.value();
        errors.put("message", exception.getMessage());
        errors.put("stacktrace", exception.fillInStackTrace().toString());
        return new ExceptionBody(status, errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(final ResourceNotFoundException exception) {
        status = HttpStatus.NOT_FOUND.value();
        errors.put("message", exception.getMessage());
        errors.put("stacktrace", exception.fillInStackTrace().toString());
        return new ExceptionBody(status, errors);
    }

    @ExceptionHandler(BirthDateParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBirthdateParsingFailed(ValidationFailedException exception) {
        status = HttpStatus.BAD_REQUEST.value();
        errors.put("message", exception.getMessage());
        errors.put("stacktrace", exception.fillInStackTrace().toString());
        return new ExceptionBody(status, errors);
    }

    @ExceptionHandler(MappingProcessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleMappingProcessException(MappingProcessException exception) {
        status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        errors.put("message", exception.getMessage());
        errors.put("stacktrace", exception.fillInStackTrace().toString());
        return new ExceptionBody(status, errors);
    }
}
