package com.autoever.apay_store_app.data;

import com.autoever.apay_store_app.data.model.api.LoginRequest;
import com.autoever.apay_store_app.data.model.api.LoginResponse;
import com.autoever.apay_store_app.data.remote.RepoService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final RepoService mRepoService;

    @Inject
    public AppDataManager(RepoService mRepoService) {
        this.mRepoService = mRepoService;
    }

    @Override
    public Single<LoginResponse> doLoginCall(LoginRequest loginRequest) {
        return mRepoService.doLoginCall(loginRequest);
    }
}

