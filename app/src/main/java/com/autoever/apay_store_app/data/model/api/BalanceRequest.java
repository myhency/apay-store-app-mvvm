package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceRequest {

    @Expose
    @SerializedName("tokenSystemId")
    private String tokenSystemId;

    @Expose
    @SerializedName("subscriberId")
    private String subscriberId;

    public BalanceRequest(String tokenSystemId, String subscriberId) {
        this.tokenSystemId = tokenSystemId;
        this.subscriberId = subscriberId;
    }

    public String getTokenSystemId() {
        return tokenSystemId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }
}
