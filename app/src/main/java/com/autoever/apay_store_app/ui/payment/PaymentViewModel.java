package com.autoever.apay_store_app.ui.payment;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    public PaymentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
