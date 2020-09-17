package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QrStoreDynamicRequest {

    @Expose
    @SerializedName("paymentSystemId")
    private Long paymentSystemId;

    public QrStoreDynamicRequest(Long paymentSystemId) {
        this.paymentSystemId = paymentSystemId;
    }

    public Long getPaymentSystemId() {
        return paymentSystemId;
    }
}

