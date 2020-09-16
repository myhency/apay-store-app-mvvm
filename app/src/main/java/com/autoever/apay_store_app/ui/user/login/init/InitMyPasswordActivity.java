package com.autoever.apay_store_app.ui.user.login.init;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityInitMyPasswordBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;

import javax.inject.Inject;

public class InitMyPasswordActivity extends BaseActivity<ActivityInitMyPasswordBinding, InitMyPasswordViewModel> implements InitMyPasswordNavigator {

    private ActivityInitMyPasswordBinding mActivityInitMyPasswordBinding;
    private InitMyPasswordViewModel mInitMyPasswordViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, InitMyPasswordActivity.class);
        return intent;
    }

    @Inject
    ViewModelProviderFactory factory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_init_my_password;
    }

    @Override
    public InitMyPasswordViewModel getViewModel() {
        mInitMyPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(InitMyPasswordViewModel.class);
        return mInitMyPasswordViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityInitMyPasswordBinding = getViewDataBinding();
        mInitMyPasswordViewModel.setNavigator(this);
        setup();
    }

    private void setup() {
        //통신사 선택 dropdown 박스 세팅
        String[] TELECOM_FIRMS = new String[]{"SKT", "KT", "LGT", "알뜰폰"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_menu_popup_item,
                TELECOM_FIRMS
        );
        mActivityInitMyPasswordBinding.filledExposedDropdown.setAdapter(adapter);

        //휴대폰 번호 입력 확인
        mActivityInitMyPasswordBinding.telephoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (mActivityInitMyPasswordBinding.telephoneNumber.getText().toString().isEmpty()) {
                    mActivityInitMyPasswordBinding.telephoneNumberTextInputLayout.setErrorEnabled(true);
                    mActivityInitMyPasswordBinding.telephoneNumber.setError("휴대폰 번호를 입력해 주세요.");
                }
            } else {
                mActivityInitMyPasswordBinding.telephoneNumberTextInputLayout.setErrorEnabled(false);
            }
        });

        //유저명 입력 확인
        mActivityInitMyPasswordBinding.userName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (mActivityInitMyPasswordBinding.userName.getText().toString().isEmpty()) {
                    mActivityInitMyPasswordBinding.userNameTextInputLayout.setErrorEnabled(true);
                    mActivityInitMyPasswordBinding.userName.setError("사용자 이름을 입력해 주세요.");
                }
            } else {
                mActivityInitMyPasswordBinding.userNameTextInputLayout.setErrorEnabled(false);
            }
        });

        //아이디 입력 확인
        mActivityInitMyPasswordBinding.userId.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (mActivityInitMyPasswordBinding.userId.getText().toString().isEmpty()) {
                    mActivityInitMyPasswordBinding.userIdTextInputLayout.setErrorEnabled(true);
                    mActivityInitMyPasswordBinding.userId.setError("사용자 아이디를 입력해 주세요.");
                }
            } else {
                mActivityInitMyPasswordBinding.userIdTextInputLayout.setErrorEnabled(false);
            }
        });

        //가입완료 버튼을 누르면 회원가입 양식에 오류가 있는지 확인하고 오류가 없을 시 다음단계를 진행한다.
        mActivityInitMyPasswordBinding.finishTextview.setOnClickListener(v -> {
            if (mActivityInitMyPasswordBinding.userNameTextInputLayout.isErrorEnabled()
                    || mActivityInitMyPasswordBinding.telephoneNumberTextInputLayout.isErrorEnabled()
                    || mActivityInitMyPasswordBinding.userIdTextInputLayout.isErrorEnabled()
            ) {
                Toast.makeText(this, "패스워드 초기화 양식에 오류가 있습니다. \n입력값을 확인바랍니다.", Toast.LENGTH_SHORT).show();
            } else if (mActivityInitMyPasswordBinding.userName.getText().toString().isEmpty()
                    || mActivityInitMyPasswordBinding.telephoneNumber.getText().toString().isEmpty()
                    || mActivityInitMyPasswordBinding.userId.getText().toString().isEmpty()
            ) {
                Toast.makeText(this, "필수입력항목을 모두 작성바랍니다.", Toast.LENGTH_SHORT).show();
            } else {
                //TODO. 패스워드 초기화 조회해야함.
                mInitMyPasswordViewModel.doResetPasswordCall(mActivityInitMyPasswordBinding.userName.getText().toString(),
                        mActivityInitMyPasswordBinding.userId.getText().toString(),
                        mActivityInitMyPasswordBinding.telephoneNumber.getText().toString());
            }
        });
    }

    @Override
    public void openResetPasswordDialog(String password) {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.reset_password_dialog);

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
            setResult(RESULT_FIRST_USER);
            finish();
        });

        dialog.show();
    }

    @Override
    public void openFailResetPasswordDialog() {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fail_reset_password_dialog);

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
        });

        dialog.show();
    }
}