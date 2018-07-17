package com.myapp.convertcurrency.feature.convert.presenter;

public interface ConvertCurrencyPresenter {

    void loadCurrencyRate();

    void convertCurrency(String amount);
}
