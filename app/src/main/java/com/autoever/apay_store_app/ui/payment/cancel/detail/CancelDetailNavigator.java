package com.autoever.apay_store_app.ui.payment.cancel.detail;

import com.autoever.apay_store_app.data.model.api.PaymentRefundDoResponse;
import com.autoever.apay_store_app.data.model.api.PaymentRefundReadyResponse;

public interface CancelDetailNavigator {

    void handleError(Throwable throwable);

    void goNext();

    void openPaymentRefundReadyReceiptFragment(PaymentRefundDoResponse paymentRefundDoResponse);
}
