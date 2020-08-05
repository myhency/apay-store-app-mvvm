package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthTestResponse {

    @Expose
    @SerializedName("data")
    private String data;

    public String getData() {
        return data;
    }
}
