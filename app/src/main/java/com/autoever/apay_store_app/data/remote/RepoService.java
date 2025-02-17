package com.autoever.apay_store_app.data.remote;

import com.autoever.apay_store_app.data.model.api.AuthTestResponse;
import com.autoever.apay_store_app.data.model.api.BalanceResponse;
import com.autoever.apay_store_app.data.model.api.CardUseDetailResponse;
import com.autoever.apay_store_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_store_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_store_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_store_app.data.model.api.FindLoginIdRequest;
import com.autoever.apay_store_app.data.model.api.FindLoginIdResponse;
import com.autoever.apay_store_app.data.model.api.LoginIdDuplicationCheckResponse;
import com.autoever.apay_store_app.data.model.api.LoginRequest;
import com.autoever.apay_store_app.data.model.api.LoginResponse;
import com.autoever.apay_store_app.data.model.api.PaymentDetailResponse;
import com.autoever.apay_store_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_store_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.data.model.api.PaymentListResponse;
import com.autoever.apay_store_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_store_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_store_app.data.model.api.PaymentReadyUserDynamicRequest;
import com.autoever.apay_store_app.data.model.api.PaymentReadyUserDynamicResponse;
import com.autoever.apay_store_app.data.model.api.PaymentRefundDoRequest;
import com.autoever.apay_store_app.data.model.api.PaymentRefundDoResponse;
import com.autoever.apay_store_app.data.model.api.PaymentRefundReadyRequest;
import com.autoever.apay_store_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_store_app.data.model.api.QrStoreDynamicRequest;
import com.autoever.apay_store_app.data.model.api.QrStoreDynamicResponse;
import com.autoever.apay_store_app.data.model.api.ResetPasswordRequest;
import com.autoever.apay_store_app.data.model.api.ResetPasswordResponse;
import com.autoever.apay_store_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_store_app.data.model.api.UserRegisterResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepoService {

    @POST("api/v2/payment/ready")
    Single<PaymentReadyResponse> doPaymentReadyCall(@Body PaymentReadyRequest paymentReadyRequest);

    @PUT("api/v2/payment/do")
    Single<PaymentDoResponse> doPaymentDoCall(@Body PaymentDoRequest paymentDoRequest);

    @POST("api/v2/tokenSystem/charge/ready")
    Single<ChargeReadyResponse> doChargeReadyCall(@Body ChargeReadyRequest chargeReadyRequest);

    @POST("api/v2/user/tokenSystem/1")
    Single<UserRegisterResponse> doUserRegisterCall(@Body UserRegisterRequest userRegisterRequest);

    @POST("api/v2/store/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);

    @GET("api/v2/authTest/hello")
    Single<AuthTestResponse> doAuthTextCall();

    @GET("api/v2/tokenSystem/1/tokenHistories?subscriberId=4")
    Single<CardUseHistoryResponse> doHistoryTestCall();

    @GET("api/v2/user/tokenSystem/{tokenSystemId}/paymentHistories")
    Single<CardUseHistoryResponse> doCardUseHistoryCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("userId") int userId,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize,
            @Query("date") String date,
            @Query("filter") String filter
    );

    @GET("api/v2/paymentHistory/{paymentHistoryId}")
    Single<CardUseDetailResponse> doCardUseDetailCall(
            @Path("paymentHistoryId") int paymentHistoryId,
            @Query("target") String target
    );

    @PUT("api/v2/payment/refund/ready")
    Single<PaymentRefundReadyResponse> doPaymentRefundReadyCall(@Body PaymentRefundReadyRequest paymentRefundReadyRequest);

    @GET("api/v2/store/tokenSystem/{tokenSystemId}/paymentHistories")
    Single<PaymentHistoryResponse> doPaymentHistoryCall(
            @Path("tokenSystemId") Long tokenSystemId,
            @Query("storeId") Long storeId,
            @Query("date") String date,
            @Query("filter") String filter,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    @GET("api/v2/tokenSystem/{tokenSystemId}/balance")
    Single<BalanceResponse> doGetBalanceCall(
            @Path("tokenSystemId") Long tokenSystemId,
            @Query("subscriberId") Long subscriberId
    );

    @POST("api/v2/payment/ready/userDynamic")
    @Headers("No-Authentication: true")
    Single<PaymentReadyUserDynamicResponse> doPaymentReadyUserDynamic(@Body PaymentReadyUserDynamicRequest paymentReadyUserDynamicRequest);

    @GET("api/v2/store/tokenSystem/{tokenSystemId}/payment")
    Single<PaymentListResponse> doGetPaymentListCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("storeId") int storeId,
            @Query("filter") String filter,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    @PUT("api/v2/payment/refund/do")
    Single<PaymentRefundDoResponse> doPaymentRefundDoCall(@Body PaymentRefundDoRequest paymentRefundDoRequest);

    @GET("api/v2/payment/{paymentId}")
    Single<PaymentDetailResponse> doGetPaymentDetailCall(@Path("paymentId") Long paymentId, @Query("target") String target);

    @PUT("api/v2/store/resetPassword")
    @Headers("No-Authentication: true")
    Single<ResetPasswordResponse> doResetPasswordCall(@Body ResetPasswordRequest resetPasswordRequest);

    @POST("api/v2/store/findLoginId")
    @Headers("No-Authentication: true")
    Single<FindLoginIdResponse> doFindLoginIdCall(@Body FindLoginIdRequest findLoginIdRequest);

    @GET("api/v2/store/loginId/{loginId}")
    @Headers("No-Authentication: true")
    Single<LoginIdDuplicationCheckResponse> doLoginIdDuplicationCheckCall(
            @Path("loginId") String loginId
    );

    @POST("qr/storeDynamic")
    Single<QrStoreDynamicResponse> doQrStoreDynamicCall(@Body QrStoreDynamicRequest qrStoreDynamicRequest);

}
