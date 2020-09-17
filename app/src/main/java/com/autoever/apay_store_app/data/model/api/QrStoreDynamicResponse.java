package com.autoever.apay_store_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *    "qrType":3,
 *    "hashedUserId":"4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a",
 *    "hashedPaymentSystemId":"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b",
 *    "token":"eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwiZXhwIjoxNTk3MDAwMzk3fQ.8Oo3KjIoGjr4zGMY7K2-W4aaD5jbN4cFSmWOkFdh38M",
 *    "signature":"a9bd"
 * }
 */
public class QrStoreDynamicResponse {

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

    public QrStoreDynamicResponse(Long qrType, String hashedUserId, String hashedPaymentSystemId, String token, String signature) {
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

    public String parseToQrString() {
        return new StringBuilder()
                .append("{")
                .append("\"qrType\":" + "\""+getQrType()+"\",")
                .append("\"hashedUserId\":" + "\""+getHashedUserId()+"\",")
                .append("\"hashedPaymentSystemId\":" + "\""+getHashedPaymentSystemId()+"\",")
                .append("\"token\":" + "\""+getToken()+"\",")
                .append("\"signature\":" + "\""+getSignature()+"\"")
                .append("}").toString();
    }
}
