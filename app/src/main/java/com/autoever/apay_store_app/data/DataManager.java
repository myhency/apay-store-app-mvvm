package com.autoever.apay_store_app.data;

import com.autoever.apay_store_app.data.remote.RepoService;

public interface DataManager extends RepoService {

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
