package com.autoever.apay_store_app.ui.payment.price;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PriceFragmentProvider {

    @ContributesAndroidInjector
    abstract PriceFragment providePriceFragmentFactory();
}
