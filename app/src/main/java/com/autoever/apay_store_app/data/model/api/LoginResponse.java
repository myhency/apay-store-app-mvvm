package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "data": {
 *     "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwicm9sZSI6InVzZXIiLCJyZWZyZXNoVG9rZW4iOiJleUpoYkdjaU9pSklVekkxTmlKOS5leUpwWkNJNk5Dd2ljbTlzWlNJNkluVnpaWElpTENKcFlYUWlPakUxT1RVek5qTTNNakU0TVRNc0ltVjRjQ0k2TVRZeU56QTNNalV5TVRneE5uMC50OHNiQ2pYQnoybnFVcnQxUlN1NDhMWklHN2pULTl4Y1h3U3d0VEhiVTVzIiwiaWF0IjoxNTk1MzYzNzIxOTE4LCJleHAiOjE2MjY4OTk3MjE5MTh9.MbtibxC8TIxzLuR-Edq_1yg4vbqWJ_r9hTyIXYD6JhQ",
 *     "userId": 4
 *   }
 * }
 */
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
