package com.autoever.apay_store_app.ui.user.register.form;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import com.autoever.apay_store_app.utils.CommonUtils;

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
        mRegisterFormViewModel.setNavigator(this);

        setup();
    }

    private void setup() {

        //통신사 선택 dropdown 박스 세팅
        String[] TELECOM_FIRMS = new String[]{"SKT", "KT", "LGT", "알뜰폰"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.dropdown_menu_popup_item,
                TELECOM_FIRMS
        );
        mFragmentRegisterFormBinding.filledExposedDropdown.setAdapter(adapter);

        //사용자 아이디 중복체크
        mFragmentRegisterFormBinding.userId.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                mRegisterFormViewModel.isUserIdDuplicated(mFragmentRegisterFormBinding.userId.getText().toString());
                if (mFragmentRegisterFormBinding.userId.getText().toString().isEmpty()) {
                    mFragmentRegisterFormBinding.userIdTextInputLayout.setErrorEnabled(true);
                    mFragmentRegisterFormBinding.userIdTextInputLayout.setError("아이디를 입력해 주세요.");
                }
            } else {
                mFragmentRegisterFormBinding.userIdTextInputLayout.setErrorEnabled(false);
            }
        });

        //패스워드 입력 규칙 확인
        mFragmentRegisterFormBinding.password.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!CommonUtils.isPasswordValid(mFragmentRegisterFormBinding.password.getText().toString())) {
                    mFragmentRegisterFormBinding.passwordTextInputLayout.setErrorEnabled(true);
                    mFragmentRegisterFormBinding.passwordTextInputLayout.setError("최소 8자리이상의 대소문자, 숫자, 특수문자를 혼용해 주세요.");
                }
            } else {
                mFragmentRegisterFormBinding.passwordTextInputLayout.setErrorEnabled(false);
            }
        });

        //패스워드 확인
        mFragmentRegisterFormBinding.passwordConfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!mFragmentRegisterFormBinding.password.getText().toString().equals(mFragmentRegisterFormBinding.passwordConfirm.getText().toString())) {
                    mFragmentRegisterFormBinding.passwordConfirmTextInputLayout.setErrorEnabled(true);
                    mFragmentRegisterFormBinding.passwordConfirmTextInputLayout.setError("입력하신 패스워드와 일치하지 않습니다.");
                }
            } else {
                mFragmentRegisterFormBinding.passwordConfirmTextInputLayout.setErrorEnabled(false);
            }
        });

        //휴대폰 번호 입력 확인
        mFragmentRegisterFormBinding.telephoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (mFragmentRegisterFormBinding.telephoneNumber.getText().toString().isEmpty()) {
                    mFragmentRegisterFormBinding.telephoneNumberTextInputLayout.setErrorEnabled(true);
                    mFragmentRegisterFormBinding.telephoneNumber.setError("휴대폰 번호를 입력해 주세요.");
                }
            } else {
                mFragmentRegisterFormBinding.telephoneNumberTextInputLayout.setErrorEnabled(false);
            }
        });

        //가입완료 버튼을 누르면 회원가입 양식에 오류가 있는지 확인하고 오류가 없을 시 다음단계를 진행한다.
        mFragmentRegisterFormBinding.finishTextview.setOnClickListener(v -> {
            if (mFragmentRegisterFormBinding.userIdTextInputLayout.isErrorEnabled()
                    || mFragmentRegisterFormBinding.passwordTextInputLayout.isErrorEnabled()
                    || mFragmentRegisterFormBinding.passwordConfirmTextInputLayout.isErrorEnabled()
                    || mFragmentRegisterFormBinding.telephoneNumberTextInputLayout.isErrorEnabled()
            ) {
                Toast.makeText(getBaseActivity(), "회원가입 양식에 오류가 있어 가입을 완료할 수 없습니다.", Toast.LENGTH_SHORT).show();
            } else if (mFragmentRegisterFormBinding.userId.getText().toString().isEmpty()
                    || mFragmentRegisterFormBinding.password.getText().toString().isEmpty()
                    || mFragmentRegisterFormBinding.passwordConfirm.toString().isEmpty()
                    || mFragmentRegisterFormBinding.telephoneNumber.getText().toString().isEmpty()
            ) {
                Toast.makeText(getBaseActivity(), "필수입력항목을 모두 작성바랍니다.", Toast.LENGTH_SHORT).show();
            } else {
                JSONObject data = new JSONObject();
                try {
                    data.put("userName", "홍길동"); //TODO. 사용자 이름 입력은 없는 걸로 변경됨.
                    data.put("userId", mFragmentRegisterFormBinding.userId.getText().toString());
                    data.put("password", mFragmentRegisterFormBinding.password.getText().toString());
                    data.put("phoneNumber", mFragmentRegisterFormBinding.telephoneNumber.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
                getBaseActivity().onFragmentDetached(TAG);
            }
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
    public void openLoginActivity() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openPasswordFragment() {

    }

    @Override
    public void setupLoginIdTextFieldHelperText(boolean result) {
        if (result) { //중복된 아이디가 존재함.
            mFragmentRegisterFormBinding.userIdTextInputLayout.setErrorEnabled(true);
            mFragmentRegisterFormBinding.userIdTextInputLayout.setError("중복된 아이디가 있습니다.");
        }
    }
}