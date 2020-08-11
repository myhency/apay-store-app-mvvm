package com.autoever.apay_store_app.ui.payment;

import java.util.Date;

public interface PaymentNavigator {

    void handleError(Throwable throwable);

    void openPriceFragment(String shopCode);

    void openPriceConfirmFragment(String shopCode, int price);

    void doPaymentReady();

    void openReceiptFragment(String storeName, Date createdDate, int amount, int userBalance);
}
