package com.autoever.apay_store_app;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.ui.main.MainViewModel;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

//    private final DataManager dataManager;

    @Inject
    public ViewModelProviderFactory() {
//        this.dataManager = dataManager;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass.getName());
    }
}
