package com.myapp.business.convert.usecase;

import com.myapp.business.convert.callback.GetCurrencyCallBack;

public interface GetCurrencyRateUseCase {
    GetCurrencyRateUseCase buildUseCase();

    void executeByCallBack(GetCurrencyCallBack callBack);

    void destroy();
}
