package com.clearsolutions.mappers;

public interface MapperToModel<V, T> {
    V toModel(T t);
}
