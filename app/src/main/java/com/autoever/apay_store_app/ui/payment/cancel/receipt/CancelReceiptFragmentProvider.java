package com.autoever.apay_store_app.ui.payment.cancel.receipt;

import com.autoever.apay_store_app.ui.payment.cancel.detail.CancelDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CancelReceiptFragmentProvider {
    @ContributesAndroidInjector
    abstract CancelReceiptFragment provideCancelReceiptFragmentFactory();
}
