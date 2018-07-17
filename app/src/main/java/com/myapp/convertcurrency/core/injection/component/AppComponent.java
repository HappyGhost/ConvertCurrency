package com.myapp.convertcurrency.core.injection.component;

import com.myapp.convertcurrency.core.application.CoreApplication;
import com.myapp.convertcurrency.core.injection.module.FragmentBuilderModule;
import com.myapp.convertcurrency.core.injection.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RepositoryModule.class,
        FragmentBuilderModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(CoreApplication application);

        AppComponent build();
    }

    Retrofit retrofit();

    void inject(CoreApplication app);
}
