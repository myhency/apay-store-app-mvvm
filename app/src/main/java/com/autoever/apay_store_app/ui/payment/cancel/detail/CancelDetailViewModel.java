package com.autoever.apay_store_app.ui.payment.cancel.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.PaymentDetailResponse;
import com.autoever.apay_store_app.data.model.api.PaymentRefundDoRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.CommonUtils;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;

public class CancelDetailViewModel extends BaseViewModel<CancelDetailNavigator> {

    private final MutableLiveData<String> amountLiveData;
    private final MutableLiveData<String> storeNameLiveData;
    private final MutableLiveData<String> createdDateLiveData;
    private final MutableLiveData<String> identifierLiveData; //결제요청번호
    private final MutableLiveData<String> paymentIdLiveData; //승인번호
    private final MutableLiveData<String> balanceKRWLiveData;
    private PaymentDetailResponse paymentDetailResponse;

    public CancelDetailViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);

        amountLiveData = new MutableLiveData<>();
        storeNameLiveData = new MutableLiveData<>();
        createdDateLiveData = new MutableLiveData<>();
        paymentIdLiveData = new MutableLiveData<>();
        identifierLiveData = new MutableLiveData<>();
        balanceKRWLiveData = new MutableLiveData<>();

        loadUserBalance();
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doGetBalanceCall(1, 4)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(balanceResponse -> {
                    if (balanceResponse != null && balanceResponse.getData() != null) {
                        balanceKRWLiveData.setValue(CommonUtils.formatToKRW(balanceResponse.getData().getBalance().toString()) + " P");
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void doPaymentRefundDoCall() {
        Log.d("debug", "doPaymentRefundDoCall started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentRefundDoCall(
                        new PaymentRefundDoRequest(
                                this.paymentDetailResponse.getData().getUserId(),
                                this.paymentDetailResponse.getData().getStoreId(),
                                this.paymentDetailResponse.getData().getTokenSystemId(),
                                this.paymentDetailResponse.getData().getAmount(),
                                this.paymentDetailResponse.getData().getPaymentId(),
                                this.paymentDetailResponse.getData().getIdentifier()
                        )
                )
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentRefundDoResponse -> {
                    setIsLoading(false);
                    getNavigator().goNext();
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void doGetPaymentDetail(Long paymentId) {
        Log.d("debug", "doPaymentRefundDoCall started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetPaymentDetailCall(paymentId, "store")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentDetailResponse -> {
                    setIsLoading(false);
                    this.paymentDetailResponse = paymentDetailResponse;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");
                    amountLiveData.setValue(CommonUtils.formatToKRW(String.valueOf(paymentDetailResponse.getData().getAmount())) + " P");
                    storeNameLiveData.setValue(paymentDetailResponse.getData().getStoreName());
                    createdDateLiveData.setValue(simpleDateFormat.format(paymentDetailResponse.getData().getCreatedDate()));
                    identifierLiveData.setValue(paymentDetailResponse.getData().getIdentifier());
                    paymentIdLiveData.setValue(String.valueOf(paymentDetailResponse.getData().getPaymentId()));
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<String> getAmountLiveData() {
        return amountLiveData;
    }

    public LiveData<String> getStoreNameLiveData() {
        return storeNameLiveData;
    }

    public LiveData<String> getCreatedDateLiveData() {
        return createdDateLiveData;
    }

    public LiveData<String> getIdentifierLiveData() {
        return identifierLiveData;
    }

    public LiveData<String> getPaymentIdLiveData() {
        return paymentIdLiveData;
    }

    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }
}
