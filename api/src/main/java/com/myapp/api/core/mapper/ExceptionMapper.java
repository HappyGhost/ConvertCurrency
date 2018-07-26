package com.myapp.api.core.mapper;

import com.myapp.business.core.exception.BaseException;

import retrofit2.HttpException;
import retrofit2.Response;

public class ExceptionMapper extends BaseInfoMapper<BaseException, HttpException> {

    @Override
    public BaseException transform(HttpException httpException) {
        BaseException baseException = new BaseException();
        Response response = httpException.response();
        baseException.setHttpCode(response.code());
        baseException.setHeaders(response.headers());
        baseException.setExceptionType(BaseException.ExceptionType.HTTP_ERROR);
        //handle common error json from server
        return baseException;
    }
}
