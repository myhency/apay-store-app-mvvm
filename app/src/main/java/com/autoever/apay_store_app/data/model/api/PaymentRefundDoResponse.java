package com.autoever.apay_store_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *    "data":{
 *       "paymentHistoryId":518,
 *       "paymentId":514,
 *       "userId":4,
 *       "storeId":2,
 *       "storeName":"TestStore",
 *       "tokenSystemId":1,
 *       "amount":10000.00,
 *       "userBalance":271900.00,
 *       "storeBalance":null,
 *       "paymentStatus":"REFUND_COMPLETE",
 *       "identifier":"1600326314921",
 *       "createdDate":"2020-09-17T10:26:53.426"
 *    }
 * }
 */
public class PaymentRefundDoResponse {

    @Expose
    @SerializedName("data")
    private PaymentRefundDo data;

    public PaymentRefundDo getData() { return data; }

    public static class PaymentRefundDo {

        @Expose
        @SerializedName("paymentHistoryId")
        private Long paymentHistoryId;

        @Expose
        @SerializedName("paymentId")
        private Long paymentId;

        @Expose
        @SerializedName("userId")
        private Long userId;

        @Expose
        @SerializedName("storeId")
        private Long storeId;

        @Expose
        @SerializedName("storeName")
        private String storeName;

        @Expose
        @SerializedName("tokenSystemId")
        private Long tokenSystemId;

        @Expose
        @SerializedName("amount")
        private Long amount;

        @Expose
        @SerializedName("userBalance")
        private Long userBalance;

        @Expose
        @SerializedName("storeBalance")
        private Long storeBalance;

        @Expose
        @SerializedName("paymentStatus")
        private String paymentStatus;

        @Expose
        @SerializedName("identifier")
        private String identifier;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

        public PaymentRefundDo(Long paymentHistoryId, Long paymentId, Long userId, Long storeId, String storeName, Long tokenSystemId, Long amount, Long userBalance, Long storeBalance, String paymentStatus, String identifier, Date createdDate) {
            this.paymentHistoryId = paymentHistoryId;
            this.paymentId = paymentId;
            this.userId = userId;
            this.storeId = storeId;
            this.storeName = storeName;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.userBalance = userBalance;
            this.storeBalance = storeBalance;
            this.paymentStatus = paymentStatus;
            this.identifier = identifier;
            this.createdDate = createdDate;
        }

        public Long getPaymentHistoryId() {
            return paymentHistoryId;
        }

        public Long getPaymentId() {
            return paymentId;
        }

        public Long getUserId() {
            return userId;
        }

        public Long getStoreId() {
            return storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public Long getTokenSystemId() {
            return tokenSystemId;
        }

        public Long getAmount() {
            return amount;
        }

        public Long getUserBalance() {
            return userBalance;
        }

        public Long getStoreBalance() {
            return storeBalance;
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
