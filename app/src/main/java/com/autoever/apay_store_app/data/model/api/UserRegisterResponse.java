package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "data": {
 *     "userId": 481
 *   }
 * }
 */
public class UserRegisterResponse {

    @Expose
    @SerializedName("data")
    private UserRegister data;

    public UserRegister getData() { return data; }

    public static class UserRegister {

        @Expose
        @SerializedName("userId")
        private int userId;

        public int getUserId() {
            return userId;
        }
    }
}
