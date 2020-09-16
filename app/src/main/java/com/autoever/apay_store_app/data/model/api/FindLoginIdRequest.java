package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FindLoginIdRequest {

    @Expose
    @SerializedName("storeName")
    private String storeName;

    @Expose
    @SerializedName("businessRegistrationNumber")
    private String businessRegistrationNumber;

    public FindLoginIdRequest(String storeName, String businessRegistrationNumber) {
        this.storeName = storeName;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }
}
