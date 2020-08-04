package com.autoever.apay_store_app.data.remote;

import com.autoever.apay_store_app.data.model.api.LoginRequest;
import com.autoever.apay_store_app.data.model.api.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RepoService {

    @POST("user/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);
}
