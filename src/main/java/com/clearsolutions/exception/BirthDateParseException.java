package com.clearsolutions.exception;

import java.time.format.DateTimeParseException;

public class BirthDateParseException extends RuntimeException {
    public BirthDateParseException(String message) {
        super(message);
    }
}
