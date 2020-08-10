package com.autoever.apay_store_app.di.builder;

//import com.autoever.apay_store_app.ui.auth.AuthFragmentProvider;
//import com.autoever.apay_store_app.ui.card.CardInfoActivity;
//import com.autoever.apay_store_app.ui.card.info.CardInfoFragmentProvider;
//import com.autoever.apay_store_app.ui.card.use.CardUseActivity;
//import com.autoever.apay_store_app.ui.card.use.detail.CardUseDetailFragmentProvider;
//import com.autoever.apay_store_app.ui.card.use.history.CardUseHistoryFragmentProvider;
//import com.autoever.apay_store_app.ui.card.use.receipt.PaymentRefundReadyReceiptProvider;
//import com.autoever.apay_store_app.ui.charge.ChargeActivity;
//import com.autoever.apay_store_app.ui.charge.amount.AmountFragmentProvider;
//import com.autoever.apay_store_app.ui.home.HomeFragmentProvider;

import com.autoever.apay_store_app.ui.main.MainActivity;
//import com.autoever.apay_store_app.ui.payment.PaymentActivity;
//import com.autoever.apay_store_app.ui.payment.confirm.PriceConfirmFragmentProvider;
//import com.autoever.apay_store_app.ui.payment.price.PriceFragmentProvider;
//import com.autoever.apay_store_app.ui.payment.receipt.ReceiptFragmentProvider;
//import com.autoever.apay_store_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_store_app.ui.main.home.HomeFragmentProvider;
import com.autoever.apay_store_app.ui.payment.PaymentActivity;
import com.autoever.apay_store_app.ui.payment.confirm.PriceConfirmFragment;
import com.autoever.apay_store_app.ui.payment.confirm.PriceConfirmFragmentProvider;
import com.autoever.apay_store_app.ui.payment.price.PriceFragment;
import com.autoever.apay_store_app.ui.payment.price.PriceFragmentProvider;
import com.autoever.apay_store_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_store_app.ui.splash.SplashActivity;
import com.autoever.apay_store_app.ui.user.login.LoginActivity;
//import com.autoever.apay_store_app.ui.user.login.LoginActivity;
//import com.autoever.apay_store_app.ui.user.register.RegisterActivity;
//import com.autoever.apay_store_app.ui.user.register.form.RegisterFormFragmentProvider;
//import com.autoever.apay_store_app.ui.user.register.password.PasswordFragmentProvider;
//import com.autoever.apay_store_app.ui.user.register.terms.TermsOfServiceFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
            HomeFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract CustomScannerActivity bindCustomScannerActivity();

    @ContributesAndroidInjector(modules = {
            PriceFragmentProvider.class,
            PriceConfirmFragmentProvider.class
    })
    abstract PaymentActivity bindPaymentActivity();


//    @ContributesAndroidInjector(modules = {
//            PriceFragmentProvider.class,
//            PriceConfirmFragmentProvider.class,
//            AuthFragmentProvider.class,
//            ReceiptFragmentProvider.class})
//    abstract PaymentActivity bindPaymentActivity();
//
//    @ContributesAndroidInjector(modules = {
//            AmountFragmentProvider.class,
//            AuthFragmentProvider.class
//    })
//    abstract ChargeActivity bindChargeActivity();
//
//    @ContributesAndroidInjector(modules = {
//            TermsOfServiceFragmentProvider.class,
//            RegisterFormFragmentProvider.class,
//            PasswordFragmentProvider.class
//    })
//    abstract RegisterActivity bindRegisterActivity();
//
    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();
//
//    @ContributesAndroidInjector(modules = {
//            CardInfoFragmentProvider.class
//    })
//    abstract CardInfoActivity bindCardInfoActivity();
//
//    @ContributesAndroidInjector(modules = {
//            CardUseHistoryFragmentProvider.class,
//            CardUseDetailFragmentProvider.class,
//            PaymentRefundReadyReceiptProvider.class
//    })
//    abstract CardUseActivity bindCardUseActivity();
}
