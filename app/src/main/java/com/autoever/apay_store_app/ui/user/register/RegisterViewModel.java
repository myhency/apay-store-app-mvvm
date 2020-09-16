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

    public void setEasyPassword(String password) {
        setIsLoading(true);
        getDataManager().setEasyPassword(password);
    }

    public void isUserIdDuplicated(String userId) {
        //TODO. 아이디 중복체크 api 호출 필요
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doLoginIdDuplicationCheckCall(userId)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(loginIdDuplicationCheckResponse -> {
            setIsLoading(false);
            getNavigator().setupLoginIdTextFieldHelperText(loginIdDuplicationCheckResponse.isData());
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }
}
