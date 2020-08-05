package com.autoever.apay_store_app.data.remote;

import android.util.Log;

import com.autoever.apay_store_app.data.model.api.BalanceRequest;
import com.autoever.apay_store_app.data.model.api.BalanceResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<BalanceResponse> getUserBalance(BalanceRequest balanceRequest) {
        Log.d("debug:", "ApiEndPoint.ENDPOINT_USER_BALANCE:" + ApiEndPoint.ENDPOINT_USER_BALANCE);
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_BALANCE)
                .addPathParameter("tokenSystemId", balanceRequest.getTokenSystemId())
                .addQueryParameter("subscriberId", balanceRequest.getSubscriberId())
                .build()
                .getObjectSingle(BalanceResponse.class);
    }
}
