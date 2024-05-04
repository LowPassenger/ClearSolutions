package com.clearsolutions.dto.request;

import com.clearsolutions.entity.Address;
import com.clearsolutions.validation.CustomBirthDayValidator;
import com.clearsolutions.validation.CustomEmailValidator;
import com.clearsolutions.validation.CustomPhoneNumberValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UsersRequestDto {
    @NotEmpty(message = "Enter email address")
    @CustomEmailValidator
    private String email;

    @NotEmpty(message = "Enter your First name")
    @Length(min = 2, max = 64,
            message = "First name length must be between 2 and 64 characters")
    @Pattern(regexp = "^(?!\s*$)[A-Za-z.',\s-]*$",
            message = "First name can include only Latin letters,"
                    + " special characters (',-) , spaces")
    private String firstName;

    @NotEmpty(message = "Enter your Last name")
    @Length(min = 2, max = 64,
            message = "Last name length must be between 2 and 64 characters")
    @Pattern(regexp = "^(?!\s*$)[A-Za-z.',\s-]*$",
            message = "Last name can include only Latin letters,"
                    + " special characters (',-) , spaces")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @CustomBirthDayValidator
    private LocalDate birthDate;

    private Address address;

    @CustomPhoneNumberValidator
    private String phoneNumber;
}
