package com.myapp.business.convert.usecase;

import com.myapp.business.convert.callback.GetCurrencyCallBack;
import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.repository.ConvertCurrencyRepository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GetCurrencyRateUseCaseImpl implements GetCurrencyRateUseCase {

    ConvertCurrencyRepository mRepository;
    Observable<CurrencyExchangeInfo> mObservable;
    Disposable mDisposable;

    public GetCurrencyRateUseCaseImpl(ConvertCurrencyRepository repository) {
        mRepository = repository;
    }

    @Override
    public GetCurrencyRateUseCase buildUseCase() {
        mObservable = mRepository.getCurrencyExchange();
        return this;
    }

    @Override
    public void executeByCallBack(final GetCurrencyCallBack callBack) {
        mDisposable = mObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CurrencyExchangeInfo>() {
                    @Override
                    public void accept(CurrencyExchangeInfo currencyExchangeInfo) {
                        callBack.onSuccess(currencyExchangeInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        callBack.onError(throwable);
                    }
                });
    }

    @Override
    public void destroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }


}
