package com.autoever.apay_store_app.ui.user.login.find;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.model.api.FindLoginIdRequest;
import com.autoever.apay_store_app.ui.base.BaseViewModel;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;

public class FindMyIdViewModel extends BaseViewModel<FindMyIdNavigator> {

    public FindMyIdViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doFindLoginIdCall(String userName, String telephoneNumber) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doFindLoginIdCall(new FindLoginIdRequest(userName, telephoneNumber))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(findLoginIdResponse -> {
            setIsLoading(false);
            getNavigator().openFindIdDialog(findLoginIdResponse.getData().getLoginId());
        }, throwable -> {
            getNavigator().openNotFoundIdDialog();
            setIsLoading(false);
        }));
    }
}
