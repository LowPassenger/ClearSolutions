package com.clearsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class ClearSolutionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClearSolutionsApplication.class, args);
    }
}
