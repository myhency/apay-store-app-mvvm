package com.autoever.apay_store_app.ui.user.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityLoginBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.main.MainActivity;
import com.autoever.apay_store_app.ui.user.login.find.FindMyIdActivity;
import com.autoever.apay_store_app.ui.user.login.init.InitMyPasswordActivity;
import com.autoever.apay_store_app.ui.user.register.RegisterActivity;

import javax.inject.Inject;

import retrofit2.HttpException;

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
        });

        mActivityLoginBinding.loginButton.setOnClickListener(v -> {
            hideKeyboard();
            mLoginViewModel.doLogin(
                    mActivityLoginBinding.userIdInput.getText().toString(),
                    mActivityLoginBinding.userPasswordInput.getText().toString()
            );
        });

        mActivityLoginBinding.findId.setOnClickListener(v -> {
            openFindMyIdActivity();
        });

        mActivityLoginBinding.passwordInit.setOnClickListener(v -> {
            openInitMyPasswordActivity();
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
    }

    @Override
    public void openFindMyIdActivity() {
        Log.d("debug", "openFindMyIdActivity");
        Intent intent = FindMyIdActivity.newIntent(LoginActivity.this);
        startActivity(intent);
    }

    @Override
    public void openInitMyPasswordActivity() {
        Log.d("debug", "openInitMyPasswordActivity");
        Intent intent = InitMyPasswordActivity.newIntent(LoginActivity.this);
        startActivity(intent);
    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO. response code 에 따라서 처리해야 함.
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            case 404:
            case 400:
                Toast.makeText(this, "로그인에 실패하였습니다.\n아이디와 비밀번호를 다시 확인바랍니다.", Toast.LENGTH_SHORT).show();
                break;
            case 500:
                Toast.makeText(this, "시스템에 문제가 발생하였습니다.\n관리자에게 문의바랍니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}