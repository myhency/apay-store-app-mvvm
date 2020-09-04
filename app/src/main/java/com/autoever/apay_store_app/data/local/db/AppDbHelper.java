package com.autoever.apay_store_app.data.local.db;

import com.autoever.apay_store_app.data.model.db.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {
    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> insertUser(final User user) {
        return Observable.fromCallable(() -> {
            mAppDatabase.userDao().insert(user);
            return true;
        });
    }
}
