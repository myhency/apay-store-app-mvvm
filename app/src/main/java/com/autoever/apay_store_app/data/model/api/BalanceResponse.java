package com.autoever.apay_store_app.data.model.api;

import com.autoever.apay_store_app.utils.CommonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public final class BalanceResponse {

    @Expose
    @SerializedName("data")
    private Balance data;

    public Balance getData() {
        return data;
    }

    public static class Balance {
        @Expose
        @SerializedName("balance")
        private Integer balance;

        public Integer getBalance() {
            return balance;
        }

    }
}
