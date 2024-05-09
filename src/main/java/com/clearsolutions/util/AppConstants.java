package com.clearsolutions.util;

import com.clearsolutions.entity.Address;
import com.clearsolutions.entity.User;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;

public class AppConstants {
    public static final User FIRST_USER;
    public static final String FIRST_USER_TEST_JSON_FILE = "first_user.json";
    public static final User SECOND_USER;
    public static final String SECOND_USER_TEST_JSON_FILE = "second_user.json";
    public static final Address FIRST_USER_ADDRESS;
    public static final String FIRST_USER_ADDRESS_TEST_JSON_FILE = "first_user_address.json";
    public static final Address SECOND_USER_ADDRESS;
    public static final String SECOND_USER_ADDRESS_TEST_JSON_FILE = "second_user_address.json";
    public static final LocalDate currentDate = LocalDate.now();
    public static final String dateFormat = "dd-MM-yyyy";
    @Value("${clearsolution.max.user.age}")
    private static int maxAgeYears;
    @Value("${clearsolution.user.min.registration.age}")
    private static int minAgeYears;
    public static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(dateFormat, Locale.ENGLISH);
    public static final LocalDate minAvailableBirthDate = currentDate
            .minus(Period.ofYears(maxAgeYears));
    public static final LocalDate maxAvailableBirthDate = currentDate
            .minus(Period.ofYears(minAgeYears));

    static {
        FIRST_USER_ADDRESS = Address.builder()
                .id(1L)
                .zipCode("322-223-322")
                .city("Copacabana")
                .concreteAddress("Yellow Str., 12b, app.18")
                .build();

        SECOND_USER_ADDRESS = Address.builder()
                .id(2L)
                .zipCode("322-223-322")
                .city("Copacabana")
                .concreteAddress("Yellow Str., 12b, app.19")
                .build();

        LocalDate firstUserBirthDate = LocalDate.parse("12-07-1993", AppConstants.formatter);
        LocalDate secondUserBirthDate = LocalDate.parse("29-11-1996", AppConstants.formatter);

        FIRST_USER = User.builder()
                .id(1L).email("lowpassenger93@gmail.com")
                .firstName("Low")
                .lastName("Passenger")
                .address(FIRST_USER_ADDRESS)
                .birthDate(firstUserBirthDate)
                .build();

        SECOND_USER = User.builder()
                .id(2L).email("pickmeup@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .address(SECOND_USER_ADDRESS)
                .birthDate(secondUserBirthDate)
                .build();
    }
}
