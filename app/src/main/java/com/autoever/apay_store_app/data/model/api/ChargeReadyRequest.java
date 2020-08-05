package com.autoever.apay_store_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "userId": 4,
 *   "tokenSystemId": 1,
 *   "trPrice": 10000
 * }
 */
public class ChargeReadyRequest {

    @Expose
    @SerializedName("userId")
    private String userId;

    @Expose
    @SerializedName("tokenSystemId")
    private String tokenSystemId;

    @Expose
    @SerializedName("trPrice")
    private int trPrice;

    public ChargeReadyRequest(String userId, String tokenSystemId, int trPrice) {
        this.userId = userId;
        this.tokenSystemId = tokenSystemId;
        this.trPrice = trPrice;
    }

    public String getUserId() {
        return userId;
    }

    public String getTokenSystemId() {
        return tokenSystemId;
    }

    public int getTrPrice() {
        return trPrice;
    }
}
