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
    private Long userId;

    @Expose
    @SerializedName("storeId")
    private Long storeId;

    @Expose
    @SerializedName("tokenSystemId")
    private Long tokenSystemId;

    @Expose
    @SerializedName("amount")
    private Long amount;

    @Expose
    @SerializedName("paymentId")
    private Long paymentId;

    @Expose
    @SerializedName("identifier")
    private String identifier;

    public PaymentDoRequest(Long userId,
                            Long storeId,
                            Long tokenSystemId,
                            Long amount,
                            Long paymentId,
                            String identifier) {
        this.userId = userId;
        this.storeId = storeId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
        this.paymentId = paymentId;
        this.identifier = identifier;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public Long getTokenSystemId() {
        return tokenSystemId;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getIdentifier() {
        return identifier;
    }
}
