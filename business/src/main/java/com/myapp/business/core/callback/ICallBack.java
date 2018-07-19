package com.myapp.business.core.callback;

public interface ICallBack<T> {

    void onSuccess(T info);

    void onError(Throwable e);
}
