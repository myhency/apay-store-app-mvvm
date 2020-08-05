package com.autoever.apay_store_app.di.component;

import android.app.Application;

import com.autoever.apay_store_app.ApayStoreApp;
import com.autoever.apay_store_app.di.builder.ActivityBuilder;
import com.autoever.apay_store_app.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(ApayStoreApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
