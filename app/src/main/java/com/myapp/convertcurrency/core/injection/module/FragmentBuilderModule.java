package com.myapp.convertcurrency.core.injection.module;

import com.myapp.convertcurrency.core.injection.scope.PerView;
import com.myapp.convertcurrency.feature.convert.module.ConvertCurrencyModule;
import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @PerView
    @ContributesAndroidInjector(modules = ConvertCurrencyModule.class)
    abstract ConvertCurrencyFragment contributeConvertCurrencyFragment();
}
