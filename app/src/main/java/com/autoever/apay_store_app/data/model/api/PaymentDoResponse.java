package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 * "data": {
 * "paymentHistoryId": 435,
 * "paymentId": 434,
 * "userId": 4,
 * "storeId": 2,
 * "storeName": "TestStore",
 * "tokenSystemId": 1,
 * "amount": 1,
 * "userBalance": 1979,
 * "paymentStatus": "PAY_COMPLETE",
 * "identifier": "1594966895392",
 * "createdDate": "2020-07-17T06:23:24.22"
 * }
 * }
 */
public class PaymentDoResponse {

    @Expose
    @SerializedName("data")
    private PaymentDo data;

    public PaymentDo getData() {
        return data;
    }

    public static class PaymentDo {
        @Expose
        @SerializedName("paymentHistoryId")
        private String paymentHistoryId;

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
        @SerializedName("storeName")
        private String storeName;

        @Expose
        @SerializedName("tokenSystemId")
        private String tokenSystemId;

        @Expose
        @SerializedName("amount")
        private int amount;

        @Expose
        @SerializedName("userBalance")
        private int userBalance;

        @Expose
        @SerializedName("paymentStatus")
        private String paymentStatus;

        @Expose
        @SerializedName("identifier")
        private String identifier;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

        public PaymentDo(String paymentHistoryId,
                         String paymentId,
                         String userId,
                         String storeId,
                         String storeName,
                         String tokenSystemId,
                         int amount,
                         int userBalance,
                         String paymentStatus,
                         String identifier,
                         Date createdDate) {
            this.paymentHistoryId = paymentHistoryId;
            this.paymentId = paymentId;
            this.userId = userId;
            this.storeId = storeId;
            this.storeName = storeName;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.userBalance = userBalance;
            this.paymentStatus = paymentStatus;
            this.identifier = identifier;
            this.createdDate = createdDate;
        }

        public String getPaymentHistoryId() {
            return paymentHistoryId;
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

        public String getStoreName() {
            return storeName;
        }

        public String getTokenSystemId() {
            return tokenSystemId;
        }

        public int getAmount() {
            return amount;
        }

        public int getUserBalance() {
            return userBalance;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public String getIdentifier() {
            return identifier;
        }

        public Date getCreatedDate() {
            return createdDate;
        }
    }


}
