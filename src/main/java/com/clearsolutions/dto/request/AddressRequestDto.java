package com.clearsolutions.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    @Pattern(regexp = "^[0-9-]*$",
            message = "ZIP/Postal code must contain only numbers and special characters -")
    @JsonProperty("zipCode")
    private String zipCode;

    @NotEmpty(message = "Enter your city")
    @Length(min = 2, max = 64,
            message = "City length must be between 2 and 64 characters")
    @Pattern(regexp = "^(?!\s*$)[A-Za-z.',\s-]*$",
            message = "City can include only Latin letters, numbers and"
                    + " special characters (',-) , spaces")
    @JsonProperty("city")
    private String city;

    @Length(max = 256,
            message = "Address length must be less than 256 characters")
    @Pattern(regexp = "^(?!\s*$)[A-Za-z.',\s-]*$",
            message = "Address can include only Latin letters, numbers and"
                    + " special characters (',-) , spaces")
    @JsonProperty("address")
    private String address;
}
