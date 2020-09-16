package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordResponse {

    @Expose
    @SerializedName("data")
    private ResetPassword data;

    public ResetPasswordResponse(ResetPassword data) {
        this.data = data;
    }

    public ResetPassword getData() {
        return data;
    }

    public static class ResetPassword {

        @Expose
        @SerializedName("password")
        private String password;

        public ResetPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }
}
