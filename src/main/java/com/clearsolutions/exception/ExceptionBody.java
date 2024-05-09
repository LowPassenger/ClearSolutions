package com.clearsolutions.exception;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionBody {
    private int status;
    private Map<String, String> errors;
}
