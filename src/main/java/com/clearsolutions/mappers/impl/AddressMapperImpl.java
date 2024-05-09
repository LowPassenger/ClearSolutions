package com.clearsolutions.mappers.impl;

import com.clearsolutions.dto.request.AddressRequestDto;
import com.clearsolutions.dto.response.AddressResponseDtoData;
import com.clearsolutions.entity.Address;
import com.clearsolutions.mappers.MapperToDto;
import com.clearsolutions.mappers.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements MapperToModel<Address, AddressRequestDto>,
        MapperToDto<AddressResponseDtoData, Address> {
    @Override
    public AddressResponseDtoData toDto(Address address) {
        return AddressResponseDtoData.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .address(address.getConcreteAddress())
                .build();
    }

    @Override
    public Address toModel(AddressRequestDto addressRequestDto) {
        return Address.builder()
                .zipCode(addressRequestDto.getZipCode())
                .city(addressRequestDto.getCity())
                .concreteAddress(addressRequestDto.getAddress())
                .build();
    }
}
