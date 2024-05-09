package com.clearsolutions.mappers;

public interface MapperToDto<U,V> {
    U toDto(V v);
}
