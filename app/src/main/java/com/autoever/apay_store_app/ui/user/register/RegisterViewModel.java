package com.autoever.apay_store_app.ui.user.register;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {

    public RegisterViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void userRegister(String userName, String phoneNumber,
                              String loginId, String password,
                              boolean term1, boolean term2, boolean term3, boolean term4) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doUserRegisterCall(new UserRegisterRequest(
                userName,
                phoneNumber,
                loginId,
                password,
                term1,
                term2,
                term3,
                term4))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(userRegisterResponse -> {
            setIsLoading(false);
            getNavigator().openDialog();
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }
}
