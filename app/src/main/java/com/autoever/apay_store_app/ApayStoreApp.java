package com.autoever.apay_store_app;

import android.app.Activity;
import android.app.Application;

//import com.autoever.apay_store_app.di.component.DaggerAppComponent;

import com.autoever.apay_store_app.di.component.AppComponent;
import com.autoever.apay_store_app.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class ApayStoreApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }
}
