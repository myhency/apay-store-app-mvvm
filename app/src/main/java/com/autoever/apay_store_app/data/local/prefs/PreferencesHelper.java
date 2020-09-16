package com.autoever.apay_store_app.data.local.prefs;

import com.autoever.apay_store_app.data.DataManager;

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getAccessToken();

    void setAccessToken(String accessToken);

    void setEasyPassword(String easyPassword);

    String getEasyPassword();
}
