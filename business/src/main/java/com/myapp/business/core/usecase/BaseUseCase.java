package com.myapp.business.core.usecase;

import com.myapp.business.core.callback.ICallBack;
import com.myapp.business.core.exception.BaseException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase<T extends ICallBack<K>, K> implements IUseCase<T> {

    Disposable mDisposable;

    @Override
    public void executeByCallBack(final T callBack) {
        mDisposable = getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<K>() {
                    @Override
                    public void accept(K info) {
                        onSuccess(callBack, info);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        onError(callBack, throwable);
                    }
                });
    }

    @Override
    public void destroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    protected void onError(T callBack, Throwable e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            if (!handleErrorException(baseException, callBack)) {
                handleGenericError(baseException, callBack);
            }
        } else {
            callBack.onError(e);
        }
    }

    private void handleGenericError(BaseException e, T callBack) {
        if (e.getExceptionType() == BaseException.ExceptionType.NETWORK_ERROR) {
            callBack.onNetworkError();
        } else {
            callBack.onGenericError(e);
        }
    }

    protected abstract boolean handleErrorException(BaseException e, T callBack);

    protected void onSuccess(T callBack, K info) {
        callBack.onSuccess(info);
    }

    protected abstract Observable<K> getObservable();
}
