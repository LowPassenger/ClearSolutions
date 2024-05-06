package com.clearsolutions.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @JsonProperty("data")
    private UserDataRequestDto dataRequestDto;

    @JsonProperty("userAddress")
    private AddressRequestDto addressRequest;
}
