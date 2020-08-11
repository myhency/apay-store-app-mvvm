package com.autoever.apay_store_app.ui.payment;

import android.util.Log;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.PaymentReadyUserDynamicRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    public PaymentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doPaymentReadyUserDynamic(Long amount, String identifier, Long storeId, String userDynamicQrInfo) {
        Log.d("debug", "doPaymentReadyUserDynamic started");
        setIsLoading(true);
        Gson gson = new Gson();
        Object object = gson.fromJson(userDynamicQrInfo, PaymentReadyUserDynamicRequest.UserDynamicQrInfo.class);
        getCompositeDisposable().add(getDataManager()
        .doPaymentReadyUserDynamic(new PaymentReadyUserDynamicRequest(
            amount,
                identifier,
                storeId,
                (PaymentReadyUserDynamicRequest.UserDynamicQrInfo)object
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(paymentReadyUserDynamicResponse -> {
            Log.d("debug", "paymentReadyUserDynamicResponse");
        }, throwable -> {
            getNavigator().handleError(throwable);
        }));
    }
}
