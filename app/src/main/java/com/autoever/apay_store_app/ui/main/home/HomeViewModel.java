package com.autoever.apay_store_app.ui.main.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    private final MutableLiveData<List<PaymentHistoryResponse.PaymentHistory.Content>> paymentHistoryContentLiveData;
    private List<PaymentHistoryResponse.PaymentHistory.Content> previousContents = new ArrayList<>();

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        paymentHistoryContentLiveData = new MutableLiveData<>();
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
}
