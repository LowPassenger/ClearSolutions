package com.clearsolutions.controller;

import com.clearsolutions.dto.request.UserRequestDto;
import com.clearsolutions.dto.response.UserResponseDto;
import com.clearsolutions.entity.User;
import com.clearsolutions.mappers.impl.UserMapperImpl;
import com.clearsolutions.service.UserService;
import com.clearsolutions.util.AppConstants;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("${origin-allowed}")
public class UserController {
    private final UserService userService;
    private final UserMapperImpl userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto requestDto) {
        UserResponseDto dto = userMapper
                .toDto(userService.create(userMapper.toModel(requestDto)));
        log.info("User with id {} was successfully created", dto.getDataResponseDto().getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userMapper
                .toDto(userService
                        .update(id, userMapper.toModel(requestDto))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patch(@PathVariable("id") Long id,
                                                 @Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userMapper
                .toDto(userService
                        .patch(id, userMapper.toModel(requestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        boolean deleteUser = userService.deleteById(id);
        log.info("User with {} was deleted with result {}", id, deleteUser);
        return deleteUser ? ResponseEntity.ok("Success, deleted User by id " + id)
                : new ResponseEntity<String>("Denied, User with id " + id
                + " wasn't deleted", HttpStatus.FORBIDDEN);
    }

    @GetMapping
    public ResponseEntity<?> getUsersByBirthdateBetween(
            @Valid @RequestParam(value = "from")
            @DateTimeFormat(pattern = AppConstants.dateFormat)LocalDate dateFrom,
            @Valid @RequestParam(value = "to")
            @DateTimeFormat(pattern = AppConstants.dateFormat)LocalDate dateTo) {
        if (dateFrom.isAfter(dateTo)) {
            return new ResponseEntity<String>("Check dates correctness!", HttpStatus.BAD_REQUEST);
        }
        List<User> users = userService.getUsersByBirthdateBetween(dateFrom, dateTo);
        return ResponseEntity.ok(users
                .stream()
                .map(userMapper::toDto)
                .toList());
    }
}
