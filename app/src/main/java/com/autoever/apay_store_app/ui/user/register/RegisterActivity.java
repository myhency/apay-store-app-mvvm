package com.autoever.apay_store_app.ui.user.register;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityRegisterBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.user.login.LoginActivity;
import com.autoever.apay_store_app.ui.user.register.form.RegisterFormFragment;
import com.autoever.apay_store_app.ui.user.register.password.PasswordFragment;
import com.autoever.apay_store_app.ui.user.register.terms.TermsOfServiceFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.HttpException;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> implements RegisterNavigator, HasSupportFragmentInjector {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityRegisterBinding mActivityRegisterBinding;
    private RegisterViewModel mRegisterViewModel;
    private FragmentManager mFragmentManager;
    private JSONObject termsOfService;
    private JSONObject registerForm;
    private JSONObject password;
    private String validPassword = "";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterViewModel.class);
        return mRegisterViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "RegisterActivity onCreate");
        mActivityRegisterBinding = getViewDataBinding();
        mRegisterViewModel.setNavigator(this);

        setup();

        openTermsOfServiceFragment();
    }

    private void setup() {
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityRegisterBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void openTermsOfServiceFragment() {
        //서비스 이용 약관 동의 화면으로 이동.
        Log.d("debug", "openTermsOfServiceFragment");
        mActivityRegisterBinding.toolbarTitle.setText("서비스 이용약관 동의");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, TermsOfServiceFragment.newInstance(), TermsOfServiceFragment.TAG)
                .addToBackStack(TermsOfServiceFragment.TAG)
                .commit();
    }

    @Override
    public void openRegisterFormFragment() {
        Log.d("debug", "openRegisterFormFragment");
        mFragmentManager
                .beginTransaction()
                .replace(R.id.clRootView, RegisterFormFragment.newInstance(), RegisterFormFragment.TAG)
                .addToBackStack(RegisterFormFragment.TAG)
                .commit();
        mActivityRegisterBinding.toolbarTitle.setText("회원가입");
    }

    @Override
    public void openDialog() {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
            openPasswordFragment();
        });

        dialog.show();
    }

    @Override
    public void handleError(Throwable throwable) {
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            case 400:
                try {
                    if(httpException.response().errorBody().string().contains("AlreadyExistPhoneNumberException")) {
                        //여기서 다이얼로그를 띄워준다.
                        // custom dialog
                        final Dialog dialog = new Dialog(this);
                        dialog.setContentView(R.layout.phone_number_exists_dialog);

                        Button okButton = dialog.findViewById(R.id.ok_button);

                        okButton.setOnClickListener(v1 -> {
                            dialog.dismiss();
                            finish();
                        });

                        dialog.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            default:
                Toast.makeText(this, "시스템오류로 인해 회원가입을 진행할 수 없습니다.\n관리자에게 문의바랍니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void openPasswordFragment() {
        Log.d("debug", "openPasswordFragment");
        mActivityRegisterBinding.toolbarTitle.setText("간편비밀번호 등록");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PasswordFragment.newInstance("register"), PasswordFragment.TAG)
                .addToBackStack(PasswordFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void setupLoginIdTextFieldHelperText(boolean result) {

    }

    @Override
    public void openLoginActivity() {
        Log.d("debug", "LoginActivity Open");
        Intent intent = LoginActivity.newIntent(RegisterActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        super.onReceivedMessageFromFragment(tag, message);
        switch (tag) {
            case "TermsOfServiceFragment":
                termsOfService = message;
                break;
            case "RegisterFormFragment":
                registerForm = message;
                break;
            case "PasswordFragment":
                //건너뛰기 버튼을 눌렀을 때는 종료한다.
                if(message.has("whatToDo")) {
                    //여기서 다이얼로그를 띄워준다.
                    // custom dialog
                    final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.go_login_dialog);

                    Button okButton = dialog.findViewById(R.id.ok_button);

                    okButton.setOnClickListener(v1 -> {
                        dialog.dismiss();
                        openLoginActivity();
                    });

                    dialog.show();
                }

                //password 가 null 일 때는 확인을 띄우고 password 에 message 를 저장한다.
                if (password == null) {
                    mActivityRegisterBinding.toolbarTitle.setText("간편비밀번호 확인");
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.clRootView, PasswordFragment.newInstance("check"), PasswordFragment.TAG)
                            .addToBackStack(PasswordFragment.TAG)
                            .commitAllowingStateLoss();
                    password = message;
                    return;
                }

                //password 가 메세지랑 같을 때는 간편비번을 preps 에 저장하고 종료한다.
                if (isPasswordValid(message) && password != null) {
                    try {
                        mRegisterViewModel.setEasyPassword((password.getString("password")));
                        //여기서 다이얼로그를 띄워준다.
                        // custom dialog
                        final Dialog dialog = new Dialog(this);
                        dialog.setContentView(R.layout.go_login_dialog);

                        Button okButton = dialog.findViewById(R.id.ok_button);

                        okButton.setOnClickListener(v1 -> {
                            dialog.dismiss();
                            openLoginActivity();
                        });

                        dialog.show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(
                            RegisterActivity.this,
                            "패스워드가 일치하지 않습니다.",
                            Toast.LENGTH_SHORT
                    ).show();
                    mActivityRegisterBinding.toolbarTitle.setText("간편비밀번호 등록");
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.clRootView, PasswordFragment.newInstance("register"), PasswordFragment.TAG)
                            .addToBackStack(PasswordFragment.TAG)
                            .commitAllowingStateLoss();
                    password = null;
                }
                break;
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        switch (tag) {
            case "TermsOfServiceFragment":
                Log.d("debug", "TermsOfServiceFragment detached");
                openRegisterFormFragment();
                break;
            case "RegisterFormFragment":
                Log.d("debug", "RegisterFormFragment detached");
                try {
                    mRegisterViewModel.userRegister(
                            registerForm.getString("userName"),
                            registerForm.getString("phoneNumber"),
                            registerForm.getString("userId"),
                            registerForm.getString("password"),
                            termsOfService.getBoolean("term01"),
                            termsOfService.getBoolean("term02"),
                            termsOfService.getBoolean("term03"),
                            termsOfService.getBoolean("term04")
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "PasswordFragment":
                Log.d("debug", "PasswordFragment detached");
                break;


//                if(isPasswordValid()) {
//                    Log.d("debug", "Password valid");
//                    //TODO. 패스워드를 prefs 에 저장해야 함.
//                    openMainActivity();
//                } else {
//                    Toast.makeText(this, "입력하신 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//                    Fragment fragment = mFragmentManager.findFragmentByTag(tag);
//                    mFragmentManager
//                            .beginTransaction()
//                            .disallowAddToBackStack()
//                            .remove(fragment)
//                            .commit();
//                    openPasswordFragment();
//                    break;
//                }
        }
    }

    private boolean isPasswordValid(JSONObject message) {
        try {
            if (password.getString("password").equals(message.getString("password"))) {
                Log.d("debug", "패스워드 등록:" + password.getString("password"));
                Log.d("debug", "패스워드 확인:" + message.getString("password"));
                return true;
            } else {
                Log.d("debug", "패스워드 등록:" + password.getString("password"));
                Log.d("debug", "패스워드 확인:" + message.getString("password"));
                return false;
            }
        } catch (JSONException e) {
            return false;
//            e.printStackTrace();
        }
    }
}