package com.autoever.apay_store_app.ui.payment.cancel;

public interface CancelNavigator {

    void handleError(Throwable throwable);

    void openPaymentListDetail(Long paymentId, String paymentStatus);

    void onCompleteUpdatePaymentCancelList();
}
