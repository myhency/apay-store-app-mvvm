package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * paymentready response body
 * {
 *   "data": {
 *     "paymentId": 424,
 *     "userId": 4,
 *     "storeId": 2,
 *     "tokenSystemId": 1,
 *     "amount": 1,
 *     "paymentStatus": "PAY_READY",
 *     "identifier": "12345",
 *     "createdDate": "2020-07-16T01:07:13.896"
 *   }
 * }
 */
public class PaymentReadyResponse {

    @Expose
    @SerializedName("data")
    private PaymentReady data;

    public PaymentReady getData() { return data; }

    public static class PaymentReady {

        @Expose
        @SerializedName("paymentId")
        private String paymentId;

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
        @SerializedName("paymentStatus")
        private String paymentStatus;

        @Expose
        @SerializedName("identifier")
        private String identifier;

        @Expose
        @SerializedName("createdDate")
        private String createdDate;

        public PaymentReady(String paymentId, String userId, String storeId, String tokenSystemId, int amount, String paymentStatus, String identifier, String createdDate) {
            this.paymentId = paymentId;
            this.userId = userId;
            this.storeId = storeId;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.paymentStatus = paymentStatus;
            this.identifier = identifier;
            this.createdDate = createdDate;
        }

        public String getPaymentId() {
            return paymentId;
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

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getCreatedDate() {
            return createdDate;
        }
    }


}
