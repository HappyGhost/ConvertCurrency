package com.myapp.api.convert.repository;


import com.myapp.api.convert.mapper.CurrencyRateMapper;
import com.myapp.api.convert.model.CurrencyExchangeModel;
import com.myapp.api.convert.service.ConvertCurrencyService;
import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.repository.ConvertCurrencyRepository;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ConvertCurrencyRepositoryImpl implements ConvertCurrencyRepository {

    private ConvertCurrencyService mService;

    public ConvertCurrencyRepositoryImpl(ConvertCurrencyService service) {
        mService = service;
    }

    @Override
    public Observable<CurrencyExchangeInfo> getCurrencyExchange() {
        return mService.getCurrencyExchange().map(new Function<CurrencyExchangeModel, CurrencyExchangeInfo>() {
            @Override
            public CurrencyExchangeInfo apply(CurrencyExchangeModel model) {
                return new CurrencyRateMapper().transform(model);
            }
        });
    }
}
