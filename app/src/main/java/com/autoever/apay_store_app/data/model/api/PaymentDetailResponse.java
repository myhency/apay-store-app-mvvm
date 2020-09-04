package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PaymentDetailResponse {

    @Expose
    @SerializedName("data")
    private PaymentDetail data;

    public PaymentDetail getData() {
        return data;
    }

    public static class PaymentDetail {

        @Expose
        @SerializedName("paymentId")
        private Long paymentId;

        @Expose
        @SerializedName("userId")
        private Long userId;

        @Expose
        @SerializedName("userName")
        private String userName;

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
        @SerializedName("tokenSystemName")
        private String tokenSystemName;

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

        public PaymentDetail(Long paymentId, Long userId, String userName, Long storeId, String storeName, Long tokenSystemId, String tokenSystemName, Long amount, String paymentStatus, String identifier, Date createdDate) {
            this.paymentId = paymentId;
            this.userId = userId;
            this.userName = userName;
            this.storeId = storeId;
            this.storeName = storeName;
            this.tokenSystemId = tokenSystemId;
            this.tokenSystemName = tokenSystemName;
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

        public String getUserName() {
            return userName;
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

        public String getTokenSystemName() {
            return tokenSystemName;
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
