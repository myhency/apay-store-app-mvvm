package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "userName": "string",
 *   "phoneNumber": "string",
 *   "loginId": "string",
 *   "password": "string",
 *   "term1": true,
 *   "term2": true,
 *   "term3": true,
 *   "term4": true
 * }
 */
public class UserRegisterRequest {

    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;

    @Expose
    @SerializedName("loginId")
    private String loginId;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("term1")
    private boolean term1;

    @Expose
    @SerializedName("term2")
    private boolean term2;

    @Expose
    @SerializedName("term3")
    private boolean term3;

    @Expose
    @SerializedName("term4")
    private boolean term4;

    public UserRegisterRequest(String userName, String phoneNumber, String loginId, String password, boolean term1, boolean term2, boolean term3, boolean term4) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.loginId = loginId;
        this.password = password;
        this.term1 = term1;
        this.term2 = term2;
        this.term3 = term3;
        this.term4 = term4;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTerm1() {
        return term1;
    }

    public boolean isTerm2() {
        return term2;
    }

    public boolean isTerm3() {
        return term3;
    }

    public boolean isTerm4() {
        return term4;
    }
}
