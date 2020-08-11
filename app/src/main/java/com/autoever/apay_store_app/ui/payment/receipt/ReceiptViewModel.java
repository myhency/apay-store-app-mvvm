package com.autoever.apay_store_app.ui.payment.receipt;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class ReceiptViewModel extends BaseViewModel<ReceiptNavigator> {

    public ReceiptViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
