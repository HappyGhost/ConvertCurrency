package com.myapp.convertcurrency.core.injection.module;

import com.myapp.convertcurrency.core.retrofit.converter.JsonPureConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient2(Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(null);
        builder.followRedirects(false);
        builder.followSslRedirects(false);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideApiAdapter(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://data.fixer.io")
                .addConverterFactory(JsonPureConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
