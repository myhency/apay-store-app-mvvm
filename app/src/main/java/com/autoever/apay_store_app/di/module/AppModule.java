package com.autoever.apay_store_app.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.autoever.apay_store_app.BuildConfig;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.data.AppDataManager;
import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.local.db.AppDatabase;
import com.autoever.apay_store_app.data.local.db.AppDbHelper;
import com.autoever.apay_store_app.data.local.db.DbHelper;
import com.autoever.apay_store_app.data.local.prefs.AppPreferencesHelper;
import com.autoever.apay_store_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_store_app.data.remote.ApiHelper;
import com.autoever.apay_store_app.data.remote.AppApiHelper;
import com.autoever.apay_store_app.data.remote.RepoService;
import com.autoever.apay_store_app.di.DatabaseInfo;
import com.autoever.apay_store_app.di.PreferenceInfo;
import com.autoever.apay_store_app.utils.AppConstants;
import com.autoever.apay_store_app.utils.rx.AppSchedulerProvider;
import com.autoever.apay_store_app.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String BASE_URL = BuildConfig.BASE_URL;

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferenceHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

//    @Provides
//    @Singleton
//    RepoServiceInterceptor provideRepoServiceInterceptor() {
//        return new RepoServiceInterceptor();
//    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/spoqa_han_sans_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.zzz")
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(AppPreferencesHelper appPreferencesHelper) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request.Builder requestBuilder = request.newBuilder();

                    Log.d("debug", "token: " + appPreferencesHelper.getAccessToken());

                    if (request.header("No-Authentication") == null) {
                        // needs credentials
                        if (appPreferencesHelper.getAccessToken() == null) {
                            throw new RuntimeException("Session token should be defined for auth apis");
                        } else {
                            requestBuilder.addHeader("Authorization","Bearer " + appPreferencesHelper.getAccessToken());
                        }
                    }

                    return chain.proceed(requestBuilder.build());
                })
                .addInterceptor(logging)
                .build();
    }
}
