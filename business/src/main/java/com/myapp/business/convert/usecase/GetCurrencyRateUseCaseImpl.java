package com.myapp.business.convert.usecase;

import com.myapp.business.convert.callback.GetCurrencyCallBack;
import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.repository.ConvertCurrencyRepository;
import com.myapp.business.core.exception.BaseException;
import com.myapp.business.core.usecase.BaseUseCase;

import io.reactivex.Observable;

public class GetCurrencyRateUseCaseImpl extends BaseUseCase<GetCurrencyCallBack, CurrencyExchangeInfo> implements IGetCurrencyRateUseCase {

    ConvertCurrencyRepository mRepository;
    Observable<CurrencyExchangeInfo> mObservable;

    public GetCurrencyRateUseCaseImpl(ConvertCurrencyRepository repository) {
        mRepository = repository;
    }

    @Override
    public IGetCurrencyRateUseCase buildUseCase() {
        mObservable = mRepository.getCurrencyExchange();
        return this;
    }

    @Override
    protected boolean handleErrorException(BaseException e, GetCurrencyCallBack callBack) {
        return false;
    }

    @Override
    protected Observable<CurrencyExchangeInfo> getObservable() {
        return mObservable;
    }
}
