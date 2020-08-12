package com.autoever.apay_store_app.ui.payment.cancel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.PaymentListResponse;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class CancelViewModel extends BaseViewModel<CancelNavigator> {

    private final MutableLiveData<List<PaymentListResponse.PaymentList.Content>> paymentListContentLiveData;
    private List<PaymentListResponse.PaymentList.Content> previousContents = new ArrayList<>();


    public CancelViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        paymentListContentLiveData = new MutableLiveData<>();
    }

    public void fetchPaymentCancelRequestData(int tokenSystemId, int storeId, String filter, int pageNo, int pageSize) {
        setIsLoading(true);

        if (pageNo == 0) { //처음업데이트 하는 경우, 또는 onRefresh called from SwipeRefreshLayout 경우 item list 를 초기화 해 줌.
            previousContents = new ArrayList<>();
            setIsLoading(false);
        }

        getCompositeDisposable().add(getDataManager()
                .doGetPaymentListCall(
                        tokenSystemId,
                        storeId,
                        filter,
                        pageNo,
                        pageSize
                )
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentCancelListResponse -> {
                    setIsLoading(false);
                    if (paymentCancelListResponse != null &&
                            paymentCancelListResponse.getData() != null &&
                            paymentCancelListResponse.getData().getContents().size() > 0) {
                        previousContents.addAll(paymentCancelListResponse.getData().getContents());
                        paymentListContentLiveData.setValue(previousContents);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<PaymentListResponse.PaymentList.Content>> getPaymentListContentLiveData() {
        return paymentListContentLiveData;
    }
}
