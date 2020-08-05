package com.autoever.apay_store_app.ui.user.register.terms;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TermsOfServiceFragmentProvider {

    @ContributesAndroidInjector
    abstract TermsOfServiceFragment provideTermsOfServiceFragmentFactory();
}
