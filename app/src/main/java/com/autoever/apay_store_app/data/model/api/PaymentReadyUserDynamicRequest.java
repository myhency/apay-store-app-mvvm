package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "amount": 0,
 *   "identifier": "string",
 *   "storeId": 0,
 *   "userDynamicQrInfo": {
 *     "qrType": 0,
 *     "hashedUserId": "string",
 *     "hashedPaymentSystemId": "string",
 *     "token": "string",
 *     "signature": "string"
 *   }
 * }
 */
public class PaymentReadyUserDynamicRequest {

    @Expose
    @SerializedName("amount")
    private Long amount;

    @Expose
    @SerializedName("identifier")
    private String identifier;

    @Expose
    @SerializedName("storeId")
    private Long storeId;

    @Expose
    @SerializedName("userDynamicQrInfo")
    private UserDynamicQrInfo userDynamicQrInfo;

    public PaymentReadyUserDynamicRequest(Long amount, String identifier, Long storeId, UserDynamicQrInfo userDynamicQrInfo) {
        this.amount = amount;
        this.identifier = identifier;
        this.storeId = storeId;
        this.userDynamicQrInfo = userDynamicQrInfo;
    }

    public Long getAmount() {
        return amount;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Long getStoreId() {
        return storeId;
    }

    public UserDynamicQrInfo getUserDynamicQrInfo() {
        return userDynamicQrInfo;
    }

    public static class UserDynamicQrInfo {

        @Expose
        @SerializedName("qrType")
        private Long qrType;

        @Expose
        @SerializedName("hashedUserId")
        private String hashedUserId;

        @Expose
        @SerializedName("hashedPaymentSystemId")
        private String hashedPaymentSystemId;

        @Expose
        @SerializedName("token")
        private String token;

        @Expose
        @SerializedName("signature")
        private String signature;

        public UserDynamicQrInfo(Long qrType, String hashedUserId, String hashedPaymentSystemId, String token, String signature) {
            this.qrType = qrType;
            this.hashedUserId = hashedUserId;
            this.hashedPaymentSystemId = hashedPaymentSystemId;
            this.token = token;
            this.signature = signature;
        }

        public Long getQrType() {
            return qrType;
        }

        public String getHashedUserId() {
            return hashedUserId;
        }

        public String getHashedPaymentSystemId() {
            return hashedPaymentSystemId;
        }

        public String getToken() {
            return token;
        }

        public String getSignature() {
            return signature;
        }
    }
}
