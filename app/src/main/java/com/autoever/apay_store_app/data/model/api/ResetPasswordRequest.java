package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

    @Expose
    @SerializedName("loginId")
    private String loginId;

    @Expose
    @SerializedName("storeName")
    private String storeName;

    @Expose
    @SerializedName("businessRegistrationNumber")
    private String businessRegistrationNumber;

    public ResetPasswordRequest(String loginId, String storeName, String businessRegistrationNumber) {
        this.loginId = loginId;
        this.storeName = storeName;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }
}
