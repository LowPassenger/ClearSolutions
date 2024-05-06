package com.clearsolutions.mappers.impl;

import com.clearsolutions.dto.request.UserRequestDto;
import com.clearsolutions.dto.response.AddressResponseDtoData;
import com.clearsolutions.dto.response.UserDataResponseDto;
import com.clearsolutions.dto.response.UserResponseDto;
import com.clearsolutions.entity.Address;
import com.clearsolutions.entity.User;
import com.clearsolutions.mappers.MapperToDto;
import com.clearsolutions.mappers.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements MapperToModel<User, UserRequestDto>,
        MapperToDto<UserResponseDto, User> {
    @Override
    public UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .dataResponseDto(UserDataResponseDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .birthDate(user.getBirthDate())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .addressResponse(AddressResponseDtoData.builder()
                        .id(user.getAddress().getId())
                        .zipCode(user.getAddress().getZipCode())
                        .city(user.getAddress().getCity())
                        .address(user.getAddress().getConcreteAddress())
                        .build())
                .build();
    }

    @Override
    public User toModel(UserRequestDto userRequestDto) {
        return User.builder()
                .email(userRequestDto.getDataRequestDto().getEmail())
                .firstName(userRequestDto.getDataRequestDto().getFirstName())
                .lastName(userRequestDto.getDataRequestDto().getLastName())
                .birthDate(userRequestDto.getDataRequestDto().getBirthDate())
                .phoneNumber(userRequestDto.getDataRequestDto().getPhoneNumber())
                .address(Address.builder()
                        .zipCode(userRequestDto.getAddressRequest().getZipCode())
                        .city(userRequestDto.getAddressRequest().getCity())
                        .concreteAddress(userRequestDto.getAddressRequest().getAddress())
                        .build())
                .build();
    }
}
