package com.autoever.apay_store_app.di.module;

import android.app.Application;
import android.content.Context;

import com.autoever.apay_store_app.data.AppDataManager;
import com.autoever.apay_store_app.data.DataManager;
import com.autoever.apay_store_app.data.remote.RepoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

//    @Provides
//    @Singleton
//    DataManager provideDataManager(AppDataManager appDataManager) {
//        return appDataManager;
//    }
//
//    @Provides
//    @Singleton
//    RepoService provideRepoService(Retrofit retrofit) {
//        return retrofit.create(RepoService.class);
//    }

}
