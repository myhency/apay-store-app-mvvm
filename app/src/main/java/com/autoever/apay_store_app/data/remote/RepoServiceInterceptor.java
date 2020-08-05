package com.autoever.apay_store_app.data.remote;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class RepoServiceInterceptor implements Interceptor {
    private String sessionToken;

    @Inject
    public RepoServiceInterceptor() {
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();

        if (request.header("No-Authentication") == null) {
            // needs credentials
            if (sessionToken == null) {
                throw new RuntimeException("Session token should be defined for auth apis");
            } else {
                requestBuilder.addHeader("Authorization","Bearer " + sessionToken);
            }
        }

        return chain.proceed(requestBuilder.build());
    }
}
