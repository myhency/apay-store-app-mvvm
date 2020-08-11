package com.autoever.apay_store_app.data.remote;

import com.autoever.apay_store_app.data.model.api.AuthTestResponse;
import com.autoever.apay_store_app.data.model.api.BalanceResponse;
import com.autoever.apay_store_app.data.model.api.CardUseDetailResponse;
import com.autoever.apay_store_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_store_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_store_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_store_app.data.model.api.LoginRequest;
import com.autoever.apay_store_app.data.model.api.LoginResponse;
import com.autoever.apay_store_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_store_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_store_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_store_app.data.model.api.PaymentReadyUserDynamicRequest;
import com.autoever.apay_store_app.data.model.api.PaymentReadyUserDynamicResponse;
import com.autoever.apay_store_app.data.model.api.PaymentRefundReadyRequest;
import com.autoever.apay_store_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_store_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_store_app.data.model.api.UserRegisterResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepoService {

    @POST("payment/ready")
    Single<PaymentReadyResponse> doPaymentReadyCall(@Body PaymentReadyRequest paymentReadyRequest);

    @PUT("payment/do")
    Single<PaymentDoResponse> doPaymentDoCall(@Body PaymentDoRequest paymentDoRequest);

    @POST("tokenSystem/charge/ready")
    Single<ChargeReadyResponse> doChargeReadyCall(@Body ChargeReadyRequest chargeReadyRequest);

    @POST("user/tokenSystem/1")
    Single<UserRegisterResponse> doUserRegisterCall(@Body UserRegisterRequest userRegisterRequest);

    @POST("store/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);

    @GET("authTest/hello")
    Single<AuthTestResponse> doAuthTextCall();

    @GET("tokenSystem/1/tokenHistories?subscriberId=4")
    Single<CardUseHistoryResponse> doHistoryTestCall();

    @GET("user/tokenSystem/{tokenSystemId}/paymentHistories")
    Single<CardUseHistoryResponse> doCardUseHistoryCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("userId") int userId,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize,
            @Query("date") String date,
            @Query("filter") String filter
    );

    @GET("paymentHistory/{paymentHistoryId}")
    Single<CardUseDetailResponse> doCardUseDetailCall(
            @Path("paymentHistoryId") int paymentHistoryId,
            @Query("target") String target
    );

    @PUT("payment/refund/ready")
    Single<PaymentRefundReadyResponse> doPaymentRefundReadyCall(@Body PaymentRefundReadyRequest paymentRefundReadyRequest);

    @GET("store/tokenSystem/{tokenSystemId}/paymentHistories")
    Single<PaymentHistoryResponse> doPaymentHistoryCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("storeId") int storeId,
            @Query("date") String date,
            @Query("filter") String filter,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    @GET("tokenSystem/{tokenSystemId}/balance")
    Single<BalanceResponse> doGetBalanceCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("subscriberId") int subscriberId
    );

    @POST("payment/ready/userDynamic")
    @Headers("No-Authentication: true")
    Single<PaymentReadyUserDynamicResponse> doPaymentReadyUserDynamic(@Body PaymentReadyUserDynamicRequest paymentReadyUserDynamicRequest);
}
