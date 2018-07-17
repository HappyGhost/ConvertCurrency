package com.myapp.convertcurrency.feature.convert.presenter;

import android.text.TextUtils;

import com.myapp.business.convert.callback.GetCurrencyCallBack;
import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.info.RateInfo;
import com.myapp.business.convert.usecase.GetCurrencyRateUseCase;
import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyView;

import javax.inject.Inject;

public class ConvertCurrencyPresenterImpl implements ConvertCurrencyPresenter {

    private ConvertCurrencyView mView;
    private GetCurrencyRateUseCase mGetCurrencyRateUseCase;
    private RateInfo mRateInfo;
    private CurrencyExchangeInfo mCurrencyExchangeInfo;

    @Inject
    public ConvertCurrencyPresenterImpl(ConvertCurrencyView view, GetCurrencyRateUseCase getCurrencyRateUseCase) {
        mView = view;
        mGetCurrencyRateUseCase = getCurrencyRateUseCase;
    }

    @Override
    public void loadCurrencyRate() {
        mView.showProcessDialog();
        mGetCurrencyRateUseCase.buildUseCase().executeByCallBack(new GetCurrencyCallBack() {
            @Override
            public void onSuccess(CurrencyExchangeInfo info) {
                mCurrencyExchangeInfo = info;
                mView.showSourceData(info);
                mView.hideProcessDialog();
            }

            @Override
            public void onError(Throwable throwable) {
                mView.hideProcessDialog();
                mView.showErrorMessage();
            }
        });
    }

    @Override
    public void convertCurrency(String amount) {
        if (mRateInfo == null) {
            return;
        }

        if (TextUtils.isEmpty(amount)) {
            mView.clearTargetAmountField();
        } else {
            double value = Double.valueOf(amount);
            mView.showTargetAmountValue(value * mRateInfo.getRate());
        }
    }

    @Override
    public void onCurrencyItemSelected(int position) {
        mRateInfo = mCurrencyExchangeInfo.getRates().get(position);
        mView.showCurrencyNote(mCurrencyExchangeInfo, mRateInfo);
    }
}
