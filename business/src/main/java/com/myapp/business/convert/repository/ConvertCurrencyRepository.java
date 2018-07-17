package com.myapp.business.convert.repository;

import com.myapp.business.convert.info.CurrencyExchangeInfo;

import io.reactivex.Observable;

public interface ConvertCurrencyRepository {

    Observable<CurrencyExchangeInfo> getCurrencyExchange();
}
