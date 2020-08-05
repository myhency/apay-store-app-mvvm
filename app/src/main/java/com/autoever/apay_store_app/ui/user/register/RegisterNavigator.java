package com.autoever.apay_store_app.ui.user.register;

public interface RegisterNavigator {

    void openTermsOfServiceFragment();

    void openRegisterFormFragment();

    void openDialog();

    void handleError(Throwable throwable);

    void openPasswordFragment();

    void openMainActivity();
}
