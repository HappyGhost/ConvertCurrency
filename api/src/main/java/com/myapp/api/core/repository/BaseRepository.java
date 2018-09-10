package com.myapp.api.core.repository;

import android.support.annotation.NonNull;

import com.myapp.api.core.mapper.ExceptionMapper;
import com.myapp.business.core.exception.BaseException;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.adapter.rxjava2.HttpException;

public class BaseRepository {

    public <T> Observable<T> processRequest(@NonNull final Observable<T> observableRequest) {
        return observableRequest.onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(final Throwable throwable) {
                return Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) {
                        convertErrorToBaseException(emitter, throwable);
                    }
                });
            }
        });
    }

    private void convertErrorToBaseException(ObservableEmitter emitter, Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            emitter.onError(new ExceptionMapper().transform(httpException));
        } else if (throwable instanceof IOException) {
            BaseException baseException = new BaseException();
            baseException.setExceptionType(BaseException.ExceptionType.NETWORK_ERROR);
            emitter.onError(baseException);
        } else {
            emitter.onError(throwable);
        }
    }
}
