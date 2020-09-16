package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FindLoginIdResponse {

    @Expose
    @SerializedName("data")
    private LoginId data;

    public FindLoginIdResponse(LoginId data) {
        this.data = data;
    }

    public LoginId getData() {
        return data;
    }

    public static class LoginId {

        @Expose
        @SerializedName("loginId")
        private String loginId;

        public LoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginId() {
            return loginId;
        }
    }
}
