package com.autoever.apay_store_app.ui.payment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_store_app.data.model.api.PaymentReadyUserDynamicRequest;
import com.autoever.apay_store_app.data.model.api.QrStoreDynamicRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.CommonUtils;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    private final MutableLiveData<String> storeNameLiveData;

    private final MutableLiveData<String> createdDateLiveData;

    private final MutableLiveData<String> amountLiveData;

    private final MutableLiveData<String> balanceKRWLiveData;

    public PaymentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        storeNameLiveData = new MutableLiveData<>();
        createdDateLiveData = new MutableLiveData<>();
        amountLiveData = new MutableLiveData<>();
        balanceKRWLiveData = new MutableLiveData<>();

        loadUserBalance();
    }

    public void doPaymentReadyUserDynamic(Long amount, String identifier, String userDynamicQrInfo) {
        Log.d("debug", "doPaymentReadyUserDynamic started");
        setIsLoading(true);
        Gson gson = new Gson();
        Object object = gson.fromJson(userDynamicQrInfo, PaymentReadyUserDynamicRequest.UserDynamicQrInfo.class);
        getCompositeDisposable().add(getDataManager()
        .doPaymentReadyUserDynamic(new PaymentReadyUserDynamicRequest(
            amount,
                identifier,
                getDataManager().getCurrentUserId(),
                (PaymentReadyUserDynamicRequest.UserDynamicQrInfo)object
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(paymentReadyUserDynamicResponse -> {
            Log.d("debug", "paymentReadyUserDynamicResponse");
            setIsLoading(false);
            doPaymentDo(
                    paymentReadyUserDynamicResponse.getData().getUserId(),
                    paymentReadyUserDynamicResponse.getData().getStoreId(),
                    paymentReadyUserDynamicResponse.getData().getTokenSystemId(),
                    paymentReadyUserDynamicResponse.getData().getAmount(),
                    paymentReadyUserDynamicResponse.getData().getPaymentId(),
                    paymentReadyUserDynamicResponse.getData().getIdentifier());
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    public void doPaymentDo(Long userId, Long storeId, Long tokenSystemId, Long amount, Long paymentId, String identifier) {
        Log.d("debug", "doPayment started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentDoCall(new PaymentDoRequest(
                        userId,
                        storeId,
                        tokenSystemId,
                        amount,
                        paymentId,
                        identifier))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentDoResponse -> {
                    setIsLoading(false);
                    Log.d("debug", paymentDoResponse.toString());
                    storeNameLiveData.setValue(paymentDoResponse.getData().getStoreName());
                    amountLiveData.setValue(CommonUtils.formatToKRW(String.valueOf(paymentDoResponse.getData().getAmount())) + " P");
                    balanceKRWLiveData.setValue(CommonUtils.formatToKRW(String.valueOf(paymentDoResponse.getData().getUserBalance())) + " P");
                    getNavigator().openReceiptFragment(
                            paymentDoResponse.getData().getStoreName(),
                            paymentDoResponse.getData().getCreatedDate(),
                            paymentDoResponse.getData().getAmount(),
                            paymentDoResponse.getData().getUserBalance()
                    );
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void loadQrStoreDynamic() {
        Log.d("debug", "loadQrUserDynamic started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doQrStoreDynamicCall(new QrStoreDynamicRequest(
                        1L
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(qrStoreDynamicResponse -> {
                    setIsLoading(false);
                    Log.d("debug", qrStoreDynamicResponse.toString());
                    getNavigator().getQrStoreDynamicData(qrStoreDynamicResponse.parseToQrString());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doGetBalanceCall(1L, getDataManager().getCurrentUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(balanceResponse -> {
                    if (balanceResponse != null && balanceResponse.getData() != null) {
                        balanceKRWLiveData.setValue(CommonUtils.formatToKRW(balanceResponse.getData().getBalance().toString()) + " P");
                        Log.d("debug", "getBalance value: " + balanceResponse.getData().getBalance().toString());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }



    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }


    public LiveData<String> getStoreNameLiveData() {
        return storeNameLiveData;
    }

    public LiveData<String> getCreatedDateLiveData() {
        return createdDateLiveData;
    }

    public LiveData<String> getAmountLiveData() {
        return amountLiveData;
    }
}
