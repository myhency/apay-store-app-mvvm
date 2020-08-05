package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *   "data": {
 *     "paymentId": 539,
 *     "userId": 4,
 *     "storeId": 2,
 *     "tokenSystemId": 1,
 *     "amount": 100,
 *     "paymentStatus": "REFUND_READY",
 *     "identifier": "1595831454257",
 *     "createdDate": "2020-07-27T06:30:54"
 *   }
 * }
 */
public class PaymentRefundReadyResponse {

    @Expose
    @SerializedName("data")
    private PaymentRefundReady data;

    public PaymentRefundReady getData() {
        return data;
    }

    public static class PaymentRefundReady {

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
