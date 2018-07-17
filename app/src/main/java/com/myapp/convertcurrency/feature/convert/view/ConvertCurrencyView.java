package com.myapp.convertcurrency.feature.convert.view;

import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.info.RateInfo;

public interface ConvertCurrencyView {
    void showProcessDialog();

    void hideProcessDialog();

    void showSourceData(CurrencyExchangeInfo info);

    void showTargetData(RateInfo rateInfo);

    void showErrorMessage();
}
