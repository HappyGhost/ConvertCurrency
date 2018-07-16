package com.myapp.convertcurrency.feature.convert.module;

import com.myapp.convertcurrency.core.injection.scope.PerView;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenter;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenterImpl;
import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyFragment;
import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyView;

import dagger.Module;
import dagger.Provides;

@Module
public class ConvertCurrencyModule {

    @Provides
    @PerView
    ConvertCurrencyView provideView(ConvertCurrencyFragment fragment) {
        return fragment;
    }

    @Provides
    @PerView
    ConvertCurrencyPresenter provideConvertCurrencyPresenter(ConvertCurrencyPresenterImpl presenter) {
        return presenter;
    }
}
