package com.autoever.apay_store_app.ui.payment.confirm;

public interface PriceConfirmNavigator {

    void handleError(Throwable throwable);

    void goNext();
}
