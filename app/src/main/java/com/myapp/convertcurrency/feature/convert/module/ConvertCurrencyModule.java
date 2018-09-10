package com.myapp.convertcurrency.feature.convert.module;

import com.myapp.api.convert.repository.ConvertCurrencyRepositoryImpl;
import com.myapp.api.convert.service.ConvertCurrencyService;
import com.myapp.business.convert.repository.ConvertCurrencyRepository;
import com.myapp.business.convert.usecase.GetCurrencyRateUseCaseImpl;
import com.myapp.business.convert.usecase.IGetCurrencyRateUseCase;
import com.myapp.convertcurrency.core.injection.scope.PerView;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenter;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenterImpl;
import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyFragment;
import com.myapp.convertcurrency.feature.convert.view.ConvertCurrencyView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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

    @Provides
    @PerView
    IGetCurrencyRateUseCase provideGetCurrencyRateUseCase(ConvertCurrencyRepository repository) {
        return new GetCurrencyRateUseCaseImpl(repository);
    }

    @Provides
    @PerView
    ConvertCurrencyRepository provideConvertCurrencyRepository(Retrofit retrofit) {
        return new ConvertCurrencyRepositoryImpl(retrofit.create(ConvertCurrencyService.class));
    }
}
