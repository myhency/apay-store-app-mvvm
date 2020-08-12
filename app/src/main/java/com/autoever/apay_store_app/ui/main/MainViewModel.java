package com.autoever.apay_store_app.ui.main;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    public MainViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void logout() {
        getDataManager().setUserAsLoggedOut();
        getNavigator().openLoginActivity();
    }
}
