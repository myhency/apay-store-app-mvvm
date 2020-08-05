package com.autoever.apay_store_app.data.local.db;

import com.autoever.apay_store_app.data.model.db.User;

import io.reactivex.Observable;

public interface DbHelper {
    Observable<Boolean> insertUser(final User user);
}
