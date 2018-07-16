package com.myapp.convertcurrency.core.retrofit.converter;

import retrofit2.Converter;


public class JsonPureResponseBodyConverter<T> implements Converter<T, T> {

    @Override
    public T convert(T value) {
        return value;
    }
}
