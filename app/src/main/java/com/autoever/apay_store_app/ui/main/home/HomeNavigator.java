package com.autoever.apay_store_app.ui.main.home;

public interface HomeNavigator {

    void handleError(Throwable throwable);

    void openPaymentActivity(int whatToOpen, String shopCode);

    void onCompleteUpdatePaymentHistoryList();
}
