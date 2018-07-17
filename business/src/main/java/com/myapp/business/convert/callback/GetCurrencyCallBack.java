package com.myapp.business.convert.callback;

import com.myapp.business.convert.info.CurrencyExchangeInfo;

public interface GetCurrencyCallBack {
    void onSuccess(CurrencyExchangeInfo info);

    void onError(Throwable throwable);
}
