package com.autoever.apay_store_app.ui.payment.scanner;

public interface CustomScannerNavigator {

    void handleError(Throwable throwable);

    void getQrStoreDynamicData(String parsedQrString);
}
