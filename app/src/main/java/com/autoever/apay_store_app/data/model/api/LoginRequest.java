package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "loginId": "string",
 *   "password": "string"
 * }
 */
public class LoginRequest {

    @Expose
    @SerializedName("loginId")
    private String loginId;

    @Expose
    @SerializedName("password")
    private String password;

    public LoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}
