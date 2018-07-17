package com.myapp.api.convert.service;


import com.myapp.api.convert.model.CurrencyExchangeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ConvertCurrencyService {

    @GET("http://data.fixer.io/api/latest?access_key=de16679db2b6e3887de55349073918a3&symbols=USD,AUD,CAD,PLN,MXN")
    Observable<CurrencyExchangeModel> getCurrencyExchange();
}
