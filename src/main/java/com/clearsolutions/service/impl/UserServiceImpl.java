package com.clearsolutions.service.impl;

import com.clearsolutions.dto.request.UserRequestDto;
import com.clearsolutions.entity.User;
import com.clearsolutions.service.UserService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // Class using ResourceNotFoundException from exception package for handling database issues
    @Override
    public User create(User user) {
        // Creating new User in DB, getting id.
        return new User();
    }

    @Override
    public User update(Long id, User user) {
        // Updating all fields for User with provided id.
        return new User();
    }

    @Override
    public User patch(Long id, User partialUser) {
        // Update one or some fields of User with provided id.
        return new User();
    }

    @Override
    public boolean deleteById(Long id) {
        // Delete User with provided id and return confirm or deny for this operation.
        return true;
    }

    @Override
    public List<User> getUsersByBirthdateBetween(LocalDate fromDate, LocalDate toDate) {
        // Get from DB list of Users by Birthdate, sort them in ascending order and add to
        // List<User>.
        return Collections.emptyList();
    }
}
