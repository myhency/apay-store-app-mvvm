package com.autoever.apay_store_app.di.builder;

import com.autoever.apay_store_app.ui.main.MainActivity;
import com.autoever.apay_store_app.ui.main.home.HomeFragmentProvider;
import com.autoever.apay_store_app.ui.payment.PaymentActivity;
import com.autoever.apay_store_app.ui.payment.cancel.CancelFragmentProvider;
import com.autoever.apay_store_app.ui.payment.cancel.detail.CancelDetailFragmentProvider;
import com.autoever.apay_store_app.ui.payment.cancel.receipt.CancelReceiptFragment;
import com.autoever.apay_store_app.ui.payment.cancel.receipt.CancelReceiptFragmentProvider;
import com.autoever.apay_store_app.ui.payment.confirm.PriceConfirmFragmentProvider;
import com.autoever.apay_store_app.ui.payment.price.PriceFragmentProvider;
import com.autoever.apay_store_app.ui.payment.receipt.ReceiptFragmentProvider;
import com.autoever.apay_store_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_store_app.ui.splash.SplashActivity;
import com.autoever.apay_store_app.ui.user.login.LoginActivity;
import com.autoever.apay_store_app.ui.user.login.find.FindMyIdActivity;
import com.autoever.apay_store_app.ui.user.login.init.InitMyPasswordActivity;
import com.autoever.apay_store_app.ui.user.register.RegisterActivity;
import com.autoever.apay_store_app.ui.user.register.form.RegisterFormFragmentProvider;
import com.autoever.apay_store_app.ui.user.register.password.PasswordFragmentProvider;
import com.autoever.apay_store_app.ui.user.register.terms.TermsOfServiceFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
            TermsOfServiceFragmentProvider.class,
            RegisterFormFragmentProvider.class,
            PasswordFragmentProvider.class
    })
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = {
            HomeFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract CustomScannerActivity bindCustomScannerActivity();

    @ContributesAndroidInjector(modules = {
            PriceFragmentProvider.class,
            PriceConfirmFragmentProvider.class,
            ReceiptFragmentProvider.class,
            CancelFragmentProvider.class,
            CancelDetailFragmentProvider.class,
            CancelReceiptFragmentProvider.class
    })
    abstract PaymentActivity bindPaymentActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract FindMyIdActivity bindFindMyIdActivity();

    @ContributesAndroidInjector
    abstract InitMyPasswordActivity bindInitMyPasswordActivity();
}
