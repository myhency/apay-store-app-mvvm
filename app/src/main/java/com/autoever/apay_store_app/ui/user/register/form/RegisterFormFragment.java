package com.autoever.apay_store_app.ui.user.register.form;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentRegisterFormBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.ui.user.register.RegisterNavigator;
import com.autoever.apay_store_app.ui.user.register.RegisterViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class RegisterFormFragment extends BaseFragment<FragmentRegisterFormBinding, RegisterViewModel> implements RegisterNavigator {

    public static final String TAG = RegisterFormFragment.class.getSimpleName();

    private FragmentRegisterFormBinding mFragmentRegisterFormBinding;

    @Inject
    ViewModelProviderFactory factory;

    private RegisterViewModel mRegisterFormViewModel;

    public static RegisterFormFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFormFragment fragment = new RegisterFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register_form;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterFormViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterViewModel.class);
        return mRegisterFormViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //back button 을 눌렀을 경우 Activity 를 종료한다.
        getBaseActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getBaseActivity().finish();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentRegisterFormBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        mFragmentRegisterFormBinding.completeRegisterButton.setOnClickListener(v -> {
            JSONObject data = new JSONObject();
            try {
                data.put("userName", mFragmentRegisterFormBinding.userName.getText().toString());
                data.put("userId", mFragmentRegisterFormBinding.userId.getText().toString());
                data.put("password", mFragmentRegisterFormBinding.password.getText().toString());
                data.put("phoneNumber", mFragmentRegisterFormBinding.phoneNumberInput.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //서비스 이용약관 동의가 완료되어 회원가입 화면으로 이동.
            getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            getBaseActivity().onFragmentDetached(TAG);
        });
    }

    @Override
    public void openTermsOfServiceFragment() {

    }

    @Override
    public void openRegisterFormFragment() {

    }

    @Override
    public void openDialog() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openPasswordFragment() {

    }

    @Override
    public void openMainActivity() {

    }
}