package com.myapp.convertcurrency.feature.convert.presenter;

import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyView;

import javax.inject.Inject;

public class ConvertCurrencyPresenterImpl implements ConvertCurrencyPresenter {

    private ConvertCurrencyView mView;

    @Inject
    public ConvertCurrencyPresenterImpl(ConvertCurrencyView view) {
        mView = view;
    }
}
