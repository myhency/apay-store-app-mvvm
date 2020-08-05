package com.autoever.apay_store_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * payment do request body
 * {
 * "userId": 0,
 * "storeId": 0,
 * "tokenSystemId": 0,
 * "amount": 0,
 * "paymentId": 0,
 * "identifier": "string"
 * }
 */
public class PaymentDoRequest {

    @Expose
    @SerializedName("userId")
    private String userId;

    @Expose
    @SerializedName("storeId")
    private String storeId;

    @Expose
    @SerializedName("tokenSystemId")
    private String tokenSystemId;

    @Expose
    @SerializedName("amount")
    private int amount;

    @Expose
    @SerializedName("paymentId")
    private String paymentId;

    @Expose
    @SerializedName("identifier")
    private String identifier;

    public PaymentDoRequest(String userId,
                            String storeId,
                            String tokenSystemId,
                            int amount,
                            String paymentId,
                            String identifier) {
        this.userId = userId;
        this.storeId = storeId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
        this.paymentId = paymentId;
        this.identifier = identifier;
    }

    public String getUserId() {
        return userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getTokenSystemId() {
        return tokenSystemId;
    }

    public int getAmount() {
        return amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getIdentifier() {
        return identifier;
    }
}
