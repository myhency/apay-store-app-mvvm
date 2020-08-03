package com.autoever.apay_store_app.ui.base;

import androidx.lifecycle.ViewModel;

import com.autoever.apay_store_app.data.DataManager;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> mNavigator;
//    private final DataManager mDataManager;

//    public BaseViewModel(DataManager mDataManager) {
//        this.mDataManager = mDataManager;
//    }
    public BaseViewModel() {

    }

//    public DataManager getDataManager() {
//        return mDataManager;
//    }

    public N getNavigator() {
        return mNavigator.get();
    }
}
