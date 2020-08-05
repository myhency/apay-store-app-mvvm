package com.autoever.apay_store_app.ui.user.login;

public interface LoginNavigator {

    void openMainActivity();

    void openRegisterActivity();

    void handleError(Throwable throwable);
}
