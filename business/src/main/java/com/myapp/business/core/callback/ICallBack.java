package com.myapp.business.core.callback;

import com.myapp.business.core.exception.BaseException;

public interface ICallBack<T> {

    void onSuccess(T info);

    void onError(Throwable e);

    void onNetworkError();

    void onGenericError(BaseException e);
}
