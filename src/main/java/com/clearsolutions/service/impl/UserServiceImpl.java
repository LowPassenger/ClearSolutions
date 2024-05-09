package com.clearsolutions.service.impl;

import com.clearsolutions.entity.User;
import com.clearsolutions.service.UserService;
import com.clearsolutions.util.AppConstants;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
        return AppConstants.FIRST_USER;
    }

    @Override
    public User update(Long id, User user) {
        // Updating all fields for User with provided id.
        return AppConstants.FIRST_USER;
    }

    @Override
    public User patch(Long id, User partialUser) {
        // Update one or some fields of User with provided id.
        return AppConstants.FIRST_USER;
    }

    @Override
    public boolean deleteById(Long id) {
        // Delete User with provided id and return confirm or deny for this operation.
        return true;
    }

    @Override
    public List<User> getUsersByBirthdateBetween(LocalDate fromDate, LocalDate toDate) {
        // Get from DB list of Users by Birthdate limits, sort them in ascending order and add to
        // List<User>. For the test case let's use two test accounts from AppConstants class
        List<User> usersFromDB = List.of(AppConstants.FIRST_USER, AppConstants.SECOND_USER);
        return usersFromDB.stream()
                .sorted(Comparator.comparing(User::getBirthDate))
                .collect(Collectors.toList());
    }
}
