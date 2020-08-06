package com.autoever.apay_store_app.ui.main.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = {
            HomeFragmentModule.class
    })
    abstract HomeFragment provideHomeFragmentFactory();
}
