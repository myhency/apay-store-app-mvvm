package com.autoever.apay_store_app.ui.payment.cancel.detail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CancelDetailFragmentProvider {
    @ContributesAndroidInjector
    abstract CancelDetailFragment provideCancelDetailFragmentFactory();
}
