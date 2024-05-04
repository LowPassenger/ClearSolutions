package com.clearsolutions.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
@Tag(name = "User controller", description = "User API")
public class UsersController {
    @Value("${clearsolution.set.user.min.registration.age}")
    private int userMinAge;

    void getAge() {
        System.out.println(userMinAge);
    }
}
