package com.autoever.apay_store_app.ui.payment.receipt;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReceiptFragmentProvider {

    @ContributesAndroidInjector
    abstract ReceiptFragment provideReceiptFragmentFactory();
}
