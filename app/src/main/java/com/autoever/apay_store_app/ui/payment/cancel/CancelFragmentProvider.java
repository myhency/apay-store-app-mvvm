package com.autoever.apay_store_app.ui.payment.cancel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CancelFragmentProvider {

    @ContributesAndroidInjector(modules = {
            CancelFragmentModule.class
    })
    abstract CancelFragment providerCancelFragmentFactory();
}

