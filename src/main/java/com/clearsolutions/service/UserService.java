package com.clearsolutions.service;

import com.clearsolutions.entity.User;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User create(User user);

    User update(Long id, User newUser);

    User patch(Long id, User partialUser);

    boolean deleteById(Long id);

    List<User> getUsersByBirthdateBetween(LocalDate fromDate, LocalDate toDate);
}
