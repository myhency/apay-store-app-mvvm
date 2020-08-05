package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *   "data": {
 *     "paymentHistoryId": 540,
 *     "paymentId": 539,
 *     "userId": 4,
 *     "storeId": 2,
 *     "storeName": "TestStore",
 *     "tokenSystemId": 1,
 *     "amount": 100,
 *     "userBalance": 101857,
 *     "paymentStatus": "PAY_COMPLETE",
 *     "identifier": "1595831454257",
 *     "canceled": false,
 *     "refundRequested": false,
 *     "createdDate": "2020-07-27T06:30:54"
 *   }
 * }
 */
public class CardUseDetailResponse {

    @Expose
    @SerializedName("data")
    private CardUseDetail data;

    public CardUseDetail getData() {
        return data;
    }

    public static class CardUseDetail {

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
        @SerializedName("paymentStatus")
        private String paymentStatus;

        @Expose
        @SerializedName("identifier")
        private String identifier;

        @Expose
        @SerializedName("canceled")
        private boolean canceled;

        @Expose
        @SerializedName("refundRequested")
        private boolean refundRequested;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

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

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public String getIdentifier() {
            return identifier;
        }

        public boolean isCanceled() {
            return canceled;
        }

        public boolean isRefundRequested() {
            return refundRequested;
        }

        public Date getCreatedDate() {
            return createdDate;
        }
    }
}
