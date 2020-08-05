package com.autoever.apay_store_app.ui.user.login;

import android.util.Log;

import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.LoginRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doLogin(String userId, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doLoginCall(new LoginRequest(
                        userId, password
                ))
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                response.getData().getJwtToken(),
                                Long.valueOf(response.getData().getStoreId()),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER
                        ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                    Log.d("debug", "loginResponse token : " + loginResponse.getData().getJwtToken());
                    Log.d("debug", "loginResponse userId : " + loginResponse.getData().getStoreId());
                    getNavigator().openMainActivity();
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }
}
