package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "data": {
 *     "hdInfo": "IA_AUTHPAGE_1.0_1.0",
 *     "apiVer": "1.0",
 *     "processType": "D",
 *     "mercntId": "M2051453",
 *     "productNm": "A-money",
 *     "dutyFreeYn": "N",
 *     "ordNo": 474,
 *     "trDay": "20200720",
 *     "trTime": "134136",
 *     "trPrice": "D9F2128A1B51CF32896596B2DACF0B15",
 *     "signature": "d01c818811bb85cf1af9ba5faabaa8dce213c4ea2e7abe11be280d7fa3a56c20"
 *   }
 * }
 */
public class ChargeReadyResponse {

    @Expose
    @SerializedName("data")
    private ChargeReady data;

    public ChargeReady getData() { return data; }

    public static class ChargeReady {

        @Expose
        @SerializedName("hdInfo")
        private String hdInfo;

        @Expose
        @SerializedName("apiVer")
        private String apiVer;

        @Expose
        @SerializedName("processType")
        private String processType;

        @Expose
        @SerializedName("mercntId")
        private String mercntId;

        @Expose
        @SerializedName("productNm")
        private String productNm;

        @Expose
        @SerializedName("dutyFreeYn")
        private String dutyFreeYn;

        @Expose
        @SerializedName("ordNo")
        private int ordNo;

        @Expose
        @SerializedName("trDay")
        private String trDay;

        @Expose
        @SerializedName("trTime")
        private String trTime;

        @Expose
        @SerializedName("trPrice")
        private String trPrice;

        @Expose
        @SerializedName("signature")
        private String signature;

        public ChargeReady(String hdInfo, String apiVer, String processType, String mercntId, String productNm, String dutyFreeYn, int ordNo, String trDay, String trTime, String trPrice, String signature) {
            this.hdInfo = hdInfo;
            this.apiVer = apiVer;
            this.processType = processType;
            this.mercntId = mercntId;
            this.productNm = productNm;
            this.dutyFreeYn = dutyFreeYn;
            this.ordNo = ordNo;
            this.trDay = trDay;
            this.trTime = trTime;
            this.trPrice = trPrice;
            this.signature = signature;
        }

        public String getHdInfo() {
            return hdInfo;
        }

        public String getApiVer() {
            return apiVer;
        }

        public String getProcessType() {
            return processType;
        }

        public String getMercntId() {
            return mercntId;
        }

        public String getProductNm() {
            return productNm;
        }

        public String getDutyFreeYn() {
            return dutyFreeYn;
        }

        public int getOrdNo() {
            return ordNo;
        }

        public String getTrDay() {
            return trDay;
        }

        public String getTrTime() {
            return trTime;
        }

        public String getTrPrice() {
            return trPrice;
        }

        public String getSignature() {
            return signature;
        }
    }
}
