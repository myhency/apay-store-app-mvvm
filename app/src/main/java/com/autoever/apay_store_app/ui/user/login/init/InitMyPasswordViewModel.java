package com.autoever.apay_store_app.ui.user.login.init;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.ResetPasswordRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class InitMyPasswordViewModel extends BaseViewModel<InitMyPasswordNavigator> {

    public InitMyPasswordViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doResetPasswordCall(String userName, String userId, String telephoneNumber) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doResetPasswordCall(new ResetPasswordRequest(userId,userName,telephoneNumber))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(resetPasswordResponse -> {
            setIsLoading(false);
            getNavigator().openResetPasswordDialog(resetPasswordResponse.getData().getPassword());
        }, throwable -> {
            setIsLoading(false);
            getNavigator().openFailResetPasswordDialog();
        }));
    }
}
