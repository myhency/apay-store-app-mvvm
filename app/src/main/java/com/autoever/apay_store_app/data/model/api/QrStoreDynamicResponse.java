package com.autoever.apay_store_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *    "qrType":2,
 *    "hashedStoreId":"d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35",
 *    "hashedPaymentSystemId":"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b",
 *    "token":"eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNjAwNDcwMzQ3MzYyfQ.JOESIrQqJtwvfgVuaVRiTFnnwdE0GNU1FwM4Ln0ZP5Y",
 *    "signature":"c33f"
 * }
 */
public class QrStoreDynamicResponse {

    @Expose
    @SerializedName("qrType")
    private Long qrType;

    @Expose
    @SerializedName("hashedStoreId")
    private String hashedStoreId;

    @Expose
    @SerializedName("hashedPaymentSystemId")
    private String hashedPaymentSystemId;

    @Expose
    @SerializedName("token")
    private String token;

    @Expose
    @SerializedName("signature")
    private String signature;

    public QrStoreDynamicResponse(Long qrType, String hashedStoreId, String hashedPaymentSystemId, String token, String signature) {
        this.qrType = qrType;
        this.hashedStoreId = hashedStoreId;
        this.hashedPaymentSystemId = hashedPaymentSystemId;
        this.token = token;
        this.signature = signature;
    }

    public Long getQrType() {
        return qrType;
    }

    public String getHashedStoreId() {
        return hashedStoreId;
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

    public String parseToQrString() {
        return new StringBuilder()
                .append("{")
                .append("\"qrType\":" + "\""+getQrType()+"\",")
                .append("\"hashedStoreId\":" + "\""+getHashedStoreId()+"\",")
                .append("\"hashedPaymentSystemId\":" + "\""+getHashedPaymentSystemId()+"\",")
                .append("\"token\":" + "\""+getToken()+"\",")
                .append("\"signature\":" + "\""+getSignature()+"\"")
                .append("}").toString();
    }
}
