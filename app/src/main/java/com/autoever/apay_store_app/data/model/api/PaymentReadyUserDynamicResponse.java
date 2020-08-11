package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *    "data":{
 *       "paymentId":42,
 *       "userId":4,
 *       "storeId":2,
 *       "tokenSystemId":1,
 *       "amount":1000,
 *       "paymentStatus":"PAY_READY",
 *       "identifier":"1597128174577",
 *       "createdDate":"2020-08-11T06:42:54.023"
 *    }
 * }
 */
public class PaymentReadyUserDynamicResponse {

    @Expose
    @SerializedName("data")
    private PaymentReadyUserDynamic data;

    public PaymentReadyUserDynamic getData() {
        return data;
    }

    public static class PaymentReadyUserDynamic {

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
        @SerializedName("tokenSystemId")
        private Long tokenSystemId;

        @Expose
        @SerializedName("amount")
        private Long amount;

        @Expose
        @SerializedName("paymentStatus")
        private String paymentStatus;

        @Expose
        @SerializedName("identifier")
        private String identifier;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

        public PaymentReadyUserDynamic(Long paymentId, Long userId, Long storeId, Long tokenSystemId, Long amount, String paymentStatus, String identifier, Date createdDate) {
            this.paymentId = paymentId;
            this.userId = userId;
            this.storeId = storeId;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.paymentStatus = paymentStatus;
            this.identifier = identifier;
            this.createdDate = createdDate;
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

        public Long getTokenSystemId() {
            return tokenSystemId;
        }

        public Long getAmount() {
            return amount;
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
