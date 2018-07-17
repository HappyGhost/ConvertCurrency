package com.myapp.api.core.mapper;

public abstract class BaseInfoMapper<T, R> {
    public abstract T transform(R value);
}
