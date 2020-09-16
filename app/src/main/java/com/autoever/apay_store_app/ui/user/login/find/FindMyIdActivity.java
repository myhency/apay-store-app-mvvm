package com.autoever.apay_store_app.ui.user.login.find;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityFindMyIdBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;

import javax.inject.Inject;

public class FindMyIdActivity extends BaseActivity<ActivityFindMyIdBinding, FindMyIdViewModel> implements FindMyIdNavigator {

    private ActivityFindMyIdBinding mActivityFindMyIdBinding;
    private FindMyIdViewModel mFindMyIdViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, FindMyIdActivity.class);
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
        return R.layout.activity_find_my_id;
    }

    @Override
    public FindMyIdViewModel getViewModel() {
        mFindMyIdViewModel = ViewModelProviders.of(this, factory)
                .get(FindMyIdViewModel.class);
        return mFindMyIdViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityFindMyIdBinding = getViewDataBinding();
        mFindMyIdViewModel.setNavigator(this);

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
        mActivityFindMyIdBinding.filledExposedDropdown.setAdapter(adapter);


        //휴대폰 번호 입력 확인
        mActivityFindMyIdBinding.telephoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (mActivityFindMyIdBinding.telephoneNumber.getText().toString().isEmpty()) {
                    mActivityFindMyIdBinding.telephoneNumberTextInputLayout.setErrorEnabled(true);
                    mActivityFindMyIdBinding.telephoneNumber.setError("휴대폰 번호를 입력해 주세요.");
                }
            } else {
                mActivityFindMyIdBinding.telephoneNumberTextInputLayout.setErrorEnabled(false);
            }
        });

        //가입완료 버튼을 누르면 회원가입 양식에 오류가 있는지 확인하고 오류가 없을 시 다음단계를 진행한다.
        mActivityFindMyIdBinding.finishTextview.setOnClickListener(v -> {
            if (mActivityFindMyIdBinding.userNameTextInputLayout.isErrorEnabled()
                    || mActivityFindMyIdBinding.telephoneNumberTextInputLayout.isErrorEnabled()
            ) {
                Toast.makeText(this, "아이디찾기 양식에 오류가 있습니다. \n입력값을 확인바랍니다.", Toast.LENGTH_SHORT).show();
            } else if (mActivityFindMyIdBinding.userName.getText().toString().isEmpty()
                    || mActivityFindMyIdBinding.telephoneNumber.getText().toString().isEmpty()
            ) {
                Toast.makeText(this, "필수입력항목을 모두 작성바랍니다.", Toast.LENGTH_SHORT).show();
            } else {
                //TODO. 아이디찾기 조회해야함.
                mFindMyIdViewModel.doFindLoginIdCall(mActivityFindMyIdBinding.userName.getText().toString(), mActivityFindMyIdBinding.telephoneNumber.getText().toString());
            }
        });
    }

    @Override
    public void openFindIdDialog(String userId) {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.find_login_id_dialog);
        TextView textView = dialog.findViewById(R.id.find_login_id_textview);
        textView.setText(userId);

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
            setResult(RESULT_FIRST_USER);
            finish();
        });

        dialog.show();
    }

    @Override
    public void openNotFoundIdDialog() {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.not_found_login_id_dialog);

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
            setResult(RESULT_FIRST_USER);
            finish();
        });

        dialog.show();
    }
}