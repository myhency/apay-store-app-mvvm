package com.autoever.apay_store_app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivitySplashBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.main.MainActivity;
import com.autoever.apay_store_app.ui.user.login.LoginActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openMainActivity() {
        Log.d("debug", "MainActivity Open");
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Log.d("debug", "LoginActivity Open");
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.startSeeding();
    }
}
