package com.myapp.business.core.usecase;

public interface IUseCase<T> {
    void executeByCallBack(T callBack);

    void destroy();
}
