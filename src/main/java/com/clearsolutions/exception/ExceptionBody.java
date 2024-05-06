package com.clearsolutions.exception;

import java.util.Map;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExceptionBody {

    private String message;
    private Map<String, String> errors;
    public ExceptionBody(final String message) {
        this.message = message;
    }

}
