package com.autoever.apay_store_app.ui.payment.scanner;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.QrStoreDynamicRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.CommonUtils;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class CustomScannerViewModel extends BaseViewModel<CustomScannerNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;

    public CustomScannerViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        balanceKRWLiveData = new MutableLiveData<>();
        loadUserBalance();
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
}
