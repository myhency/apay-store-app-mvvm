package com.autoever.apay_store_app.data;


import com.autoever.apay_store_app.data.local.db.DbHelper;
import com.autoever.apay_store_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_store_app.data.remote.ApiHelper;
import com.autoever.apay_store_app.data.remote.RepoService;

import io.reactivex.Observable;

public interface DataManager extends DbHelper, ApiHelper, PreferencesHelper, RepoService {
    Observable<Boolean> seedDatabaseQuestions();

    Observable<Boolean> getAppAccessPermissions();

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode
    );

//    void updateRepoServiceInterceptor(String accessToken);

    enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
