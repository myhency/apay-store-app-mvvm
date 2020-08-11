package com.autoever.apay_store_app.ui.payment;

public interface PaymentNavigator {

    void handleError(Throwable throwable);

    void openPriceFragment(String shopCode);

    void openPriceConfirmFragment(String shopCode, int price);

    void doPaymentReady();
}
