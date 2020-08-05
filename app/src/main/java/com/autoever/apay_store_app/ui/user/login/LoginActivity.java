package com.autoever.apay_store_app.ui.user.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityLoginBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.main.MainActivity;
import com.autoever.apay_store_app.ui.user.register.RegisterActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    public static final String TAG = LoginActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ActivityLoginBinding mActivityLoginBinding;
    private LoginViewModel mLoginViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this, factory)
                .get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "LoginActivity onCreate");
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);

        setup();
    }

    private void setup() {
        mActivityLoginBinding.registerButton.setOnClickListener(v -> {
            openRegisterActivity();
            finish();
        });

        mActivityLoginBinding.loginButton.setOnClickListener(v -> {
            mLoginViewModel.doLogin(
                    mActivityLoginBinding.userIdInput.getText().toString(),
                    mActivityLoginBinding.userPasswordInput.getText().toString()
            );
        });
    }

    @Override
    public void openMainActivity() {
        hideKeyboard();
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openRegisterActivity() {
        Log.d("debug", "MainActivity Open");
        Intent intent = RegisterActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO. response code 에 따라서 처리해야 함.
        ANError anError = (ANError) throwable;
        Log.d("debug", "anError.getErrorBody():" + anError.getErrorBody());
        Log.d("debug", "throwable message: " + throwable.getMessage());
    }
}