package com.myapp.business.core.exception;

import com.myapp.business.core.constants.ApiCode;

import okhttp3.Headers;

public class BaseException extends RuntimeException {
    private String mCode = ApiCode.UNKNOWN;
    private int mHttpCode;
    private String mMessage;
    private Headers mHeaders;
    private ExceptionType mExceptionType;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Headers getHeaders() {
        return mHeaders;
    }

    public void setHeaders(Headers headers) {
        mHeaders = headers;
    }

    public int getHttpCode() {
        return mHttpCode;
    }

    public void setHttpCode(int httpCode) {
        mHttpCode = httpCode;
    }

    public ExceptionType getExceptionType() {
        return mExceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        mExceptionType = exceptionType;
    }

    public enum ExceptionType {
        HTTP_ERROR(),
        NETWORK_ERROR()
    }
}
