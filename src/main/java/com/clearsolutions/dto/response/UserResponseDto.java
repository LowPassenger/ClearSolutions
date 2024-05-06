package com.clearsolutions.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @JsonProperty("data")
    private UserDataResponseDto dataResponseDto;

    @JsonProperty("userAddress")
    private AddressResponseDtoData addressResponse;
}
