package com.clearsolutions.util;

import com.clearsolutions.entity.Address;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;

public class AppConstants {
    @Value("${clearsolution.max.user.age}")
    private static int maxAgeYears;
    @Value("${clearsolution.user.min.registration.age}")
    private static int minAgeYears;
    public static final String dateFormat = "dd-MM-yyyy";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    public static final LocalDate currentDate = LocalDate.now();
    public static final LocalDate minAvailableBirthDate = currentDate
            .minus(Period.ofYears(maxAgeYears));
    public static final LocalDate maxAvailableBirthDate = currentDate
            .minus(Period.ofYears(minAgeYears));

}
