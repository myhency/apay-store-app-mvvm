package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginIdDuplicationCheckResponse {

    @Expose
    @SerializedName("data")
    private boolean data;

    public LoginIdDuplicationCheckResponse(boolean data) {
        this.data = data;
    }

    public boolean isData() {
        return data;
    }
}
