package com.autoever.apay_store_app.data.remote;

import com.autoever.apay_store_app.data.model.api.BalanceRequest;
import com.autoever.apay_store_app.data.model.api.BalanceResponse;

import io.reactivex.Single;

public interface ApiHelper {

    Single<BalanceResponse> getUserBalance(BalanceRequest balanceRequest);
}
