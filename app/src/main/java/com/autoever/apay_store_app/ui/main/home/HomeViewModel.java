package com.autoever.apay_store_app.ui.main.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.CommonUtils;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    private final MutableLiveData<List<PaymentHistoryResponse.PaymentHistory.Content>> paymentHistoryContentLiveData;
    private List<PaymentHistoryResponse.PaymentHistory.Content> previousContents = new ArrayList<>();
    private final MutableLiveData<String> balanceKRWLiveData;

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        paymentHistoryContentLiveData = new MutableLiveData<>();
        balanceKRWLiveData = new MutableLiveData<>();

        loadUserBalance();
    }

    public void fetchPaymentMonthlyHistoryData(int tokenSystemId, int storeId, Date date, String filter, int pageNo, int pageSize) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doPaymentHistoryCall(
                tokenSystemId,
                storeId,
                simpleDateFormat.format(date),
                filter,
                pageNo,
                pageSize)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(paymentHistoryResponse -> {
            setIsLoading(false);
            Log.d("debug", "paymentHistoryResponse");
            previousContents.addAll(paymentHistoryResponse.getData().getContents());
            paymentHistoryContentLiveData.setValue(previousContents);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    public LiveData<List<PaymentHistoryResponse.PaymentHistory.Content>> getPaymentHistoryContentLiveData() {
        return paymentHistoryContentLiveData;
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doGetBalanceCall(1, 2)
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
}
