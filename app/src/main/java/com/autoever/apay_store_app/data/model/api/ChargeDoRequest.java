package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "resultCd": "string", x
 *   "errCd": "string", x
 *   "resultMsg": "string",
 *   "mercntId": "string",
 *   "ordNo": 0,
 *   "authNo": "string",
 *   "trPrice": 0,
 *   "discntPrice": "string", default = 0
 *   "payPrice": 0, default=trPrice 랑 동일
 *   "trDay": "string",
 *   "trTime": "string",
 *   "mercntParam1": "string", OPTIONAL
 *   "mercntParam2": "string", OPTIONAL
 *   "signatureString": "string"
 * }
 */
public class ChargeDoRequest {

    @Expose
    @SerializedName("resultCd")
    private String resultCd;

    @Expose
    @SerializedName("errCd")
    private String errCd;

    @Expose
    @SerializedName("resultMsg")
    private String resultMsg;

    @Expose
    @SerializedName("mercntId")
    private String mercntId;

    @Expose
    @SerializedName("ordNo")
    private int ordNo;

    @Expose
    @SerializedName("authNo")
    private String authNo;

    @Expose
    @SerializedName("trPrice")
    private String trPrice;

    @Expose
    @SerializedName("trDay")
    private String trDay;

    @Expose
    @SerializedName("trTime")
    private String trTime;


    @Expose
    @SerializedName("mercntParam1")
    private String mercntParam1;

    @Expose
    @SerializedName("mercntParam2")
    private String mercntParam2;

    @Expose
    @SerializedName("signatureString")
    private String signatureString;

    public ChargeDoRequest(String resultCd, String errCd, String resultMsg, String mercntId, int ordNo, String authNo, String trPrice, String trDay, String trTime, String mercntParam1, String mercntParam2, String signatureString) {
        this.resultCd = resultCd;
        this.errCd = errCd;
        this.resultMsg = resultMsg;
        this.mercntId = mercntId;
        this.ordNo = ordNo;
        this.authNo = authNo;
        this.trPrice = trPrice;
        this.trDay = trDay;
        this.trTime = trTime;
        this.mercntParam1 = mercntParam1;
        this.mercntParam2 = mercntParam2;
        this.signatureString = signatureString;
    }

    public String getResultCd() {
        return resultCd;
    }

    public String getErrCd() {
        return errCd;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public String getMercntId() {
        return mercntId;
    }

    public int getOrdNo() {
        return ordNo;
    }

    public String getAuthNo() {
        return authNo;
    }

    public String getTrPrice() {
        return trPrice;
    }

    public String getTrDay() {
        return trDay;
    }

    public String getTrTime() {
        return trTime;
    }

    public String getMercntParam1() {
        return mercntParam1;
    }

    public String getMercntParam2() {
        return mercntParam2;
    }

    public String getSignatureString() {
        return signatureString;
    }
}
