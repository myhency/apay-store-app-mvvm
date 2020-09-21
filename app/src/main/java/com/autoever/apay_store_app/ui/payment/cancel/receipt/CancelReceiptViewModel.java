package com.autoever.apay_store_app.ui.payment.cancel.receipt;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class CancelReceiptViewModel extends BaseViewModel {

    public CancelReceiptViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
