package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @Expose
    @SerializedName("data")
    private Login data;

    public Login getData() { return data; }

    public static class Login {

        @Expose
        @SerializedName("jwtToken")
        private String jwtToken;

        @Expose
        @SerializedName("userId")
        private int userId;

        public String getJwtToken() {
            return jwtToken;
        }

        public int getUserId() {
            return userId;
        }
    }
}
