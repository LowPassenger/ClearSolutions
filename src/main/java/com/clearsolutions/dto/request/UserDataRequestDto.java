package com.clearsolutions.dto.request;

import com.clearsolutions.validation.CustomBirthDayValidator;
import com.clearsolutions.validation.CustomEmailValidator;
import com.clearsolutions.validation.CustomPhoneNumberValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataRequestDto {
    @NotEmpty(message = "Enter email address")
    @CustomEmailValidator
    @JsonProperty("email")
    private String email;

    @NotEmpty(message = "Enter your First name")
    @Length(min = 2, max = 64,
            message = "First name length must be between 2 and 64 characters")
    @Pattern(regexp = "^(?!\s*$)[A-Za-z.',\s-]*$",
            message = "First name can include only Latin letters,"
                    + " special characters (',-) , spaces")
    @JsonProperty("firstName")
    private String firstName;

    @NotEmpty(message = "Enter your Last name")
    @Length(min = 2, max = 64,
            message = "Last name length must be between 2 and 64 characters")
    @Pattern(regexp = "^(?!\s*$)[A-Za-z.',\s-]*$",
            message = "Last name can include only Latin letters,"
                    + " special characters (',-) , spaces")
    @JsonProperty("lastName")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @CustomBirthDayValidator
    @JsonProperty("birthDate")
    private LocalDate birthDate;

    @CustomPhoneNumberValidator
    @JsonProperty("phoneNumber")
    private String phoneNumber;
}
