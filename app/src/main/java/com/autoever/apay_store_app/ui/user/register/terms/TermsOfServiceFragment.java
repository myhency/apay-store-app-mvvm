package com.autoever.apay_store_app.ui.user.register.terms;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentTermsOfServiceBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.ui.user.register.RegisterNavigator;
import com.autoever.apay_store_app.ui.user.register.RegisterViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;


public class TermsOfServiceFragment extends BaseFragment<FragmentTermsOfServiceBinding, RegisterViewModel> implements RegisterNavigator {

    public static final String TAG = TermsOfServiceFragment.class.getSimpleName();

    private FragmentTermsOfServiceBinding mFragmentTermsOfServiceBinding;

    @Inject
    ViewModelProviderFactory factory;
    private RegisterViewModel mTermsOfServiceViewModel;

    public static TermsOfServiceFragment newInstance() {
        Bundle args = new Bundle();
        TermsOfServiceFragment fragment = new TermsOfServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_terms_of_service;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mTermsOfServiceViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterViewModel.class);
        return mTermsOfServiceViewModel;
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
        mFragmentTermsOfServiceBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        CheckBoxListener checkBoxListener = new CheckBoxListener();
        mFragmentTermsOfServiceBinding.agreeAllCheckbox.setOnCheckedChangeListener(checkBoxListener);
        mFragmentTermsOfServiceBinding.agreeTerm01Checkbox.setOnCheckedChangeListener(checkBoxListener);
        mFragmentTermsOfServiceBinding.agreeTerm02Checkbox.setOnCheckedChangeListener(checkBoxListener);
        mFragmentTermsOfServiceBinding.agreeTerm03Checkbox.setOnCheckedChangeListener(checkBoxListener);
        mFragmentTermsOfServiceBinding.agreeTerm04Checkbox.setOnCheckedChangeListener(checkBoxListener);

        mFragmentTermsOfServiceBinding.joinTermsNextButton.setOnClickListener(v -> {
            CheckBox agreeAll = mFragmentTermsOfServiceBinding.agreeAllCheckbox,
                    agreeTerm01 = mFragmentTermsOfServiceBinding.agreeTerm01Checkbox,
                    agreeTerm02 = mFragmentTermsOfServiceBinding.agreeTerm02Checkbox,
                    agreeTerm03 = mFragmentTermsOfServiceBinding.agreeTerm03Checkbox,
                    agreeTerm04 = mFragmentTermsOfServiceBinding.agreeTerm04Checkbox;

            if (!agreeTerm01.isChecked() || !agreeTerm02.isChecked() || !agreeTerm03.isChecked()) {
                //필수항목 중 하나라도 동의가 되어 있지 않은 경우
                Toast.makeText(getActivity(), "필수항목에 모두 동의하셔야 합니다.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //TODO.07 서비스 이용약관 동의에 대한 정의가 되면 수정해야 함.

                JSONObject data = new JSONObject();
                try {
                    data.put("term01", Boolean.valueOf(agreeTerm01.isChecked()));
                    data.put("term02", Boolean.valueOf(agreeTerm02.isChecked()));
                    data.put("term03", Boolean.valueOf(agreeTerm03.isChecked()));
                    data.put("term04", Boolean.valueOf(agreeTerm04.isChecked()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //서비스 이용약관 동의가 완료되어 회원가입 화면으로 이동.
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
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openPasswordFragment() {

    }

    @Override
    public void openMainActivity() {

    }

    class CheckBoxListener implements CheckBox.OnCheckedChangeListener {
        CheckBox agreeAll = mFragmentTermsOfServiceBinding.agreeAllCheckbox,
                agreeTerm01 = mFragmentTermsOfServiceBinding.agreeTerm01Checkbox,
                agreeTerm02 = mFragmentTermsOfServiceBinding.agreeTerm02Checkbox,
                agreeTerm03 = mFragmentTermsOfServiceBinding.agreeTerm03Checkbox,
                agreeTerm04 = mFragmentTermsOfServiceBinding.agreeTerm04Checkbox;

        final ArrayList<CheckBox> agreeTermCheckBoxList = new ArrayList<>(Arrays.asList(
                agreeAll, agreeTerm01, agreeTerm02, agreeTerm03, agreeTerm04
        ));

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == agreeAll) {
                if (isChecked) {
                    //"모두 동의합니다" 버튼을 눌렀을 때는 모든 약관동의 버튼에 체크한다.
                    agreeAll.setButtonDrawable(R.drawable.icon_check);
                    for (CheckBox checkBox : agreeTermCheckBoxList) {
                        checkBox.setButtonDrawable(R.drawable.icon_check);
                        checkBox.setChecked(true);
                    }
                } else {
                    //"모두 동의합니다" 버튼을 해제했을 때는 모든 약관동의 버튼에 체크해제한다.
                    agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    for (CheckBox checkBox : agreeTermCheckBoxList) {
                        checkBox.setButtonDrawable(R.drawable.icon_check_dis);
                        checkBox.setChecked(false);
                    }
                }
            } else if (buttonView == agreeTerm01) {
                if (isChecked) {
                    agreeTerm01.setButtonDrawable(R.drawable.icon_check);
                    if (!agreeTerm02.isChecked() || !agreeTerm03.isChecked() || !agreeTerm04.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    } else if (agreeTerm02.isChecked() && agreeTerm03.isChecked() && agreeTerm04.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check);
                    }
                } else {
                    agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    agreeTerm01.setButtonDrawable(R.drawable.icon_check_dis);
                }
            } else if (buttonView == agreeTerm02) {
                if (isChecked) {
                    agreeTerm02.setButtonDrawable(R.drawable.icon_check);
                    if (!agreeTerm01.isChecked() || !agreeTerm03.isChecked() || !agreeTerm04.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    } else if (agreeTerm01.isChecked() && agreeTerm03.isChecked() && agreeTerm04.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check);
                    }
                } else {
                    agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    agreeTerm02.setButtonDrawable(R.drawable.icon_check_dis);
                }
            } else if (buttonView == agreeTerm03) {
                if (isChecked) {
                    agreeTerm03.setButtonDrawable(R.drawable.icon_check);
                    if (!agreeTerm01.isChecked() || !agreeTerm02.isChecked() || !agreeTerm04.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    } else if (agreeTerm01.isChecked() && agreeTerm02.isChecked() && agreeTerm04.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check);
                    }
                } else {
                    agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    agreeTerm03.setButtonDrawable(R.drawable.icon_check_dis);
                }
            } else if (buttonView == agreeTerm04) {
                if (isChecked) {
                    agreeTerm04.setButtonDrawable(R.drawable.icon_check);
                    if (!agreeTerm01.isChecked() || !agreeTerm02.isChecked() || !agreeTerm03.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    } else if (agreeTerm01.isChecked() && agreeTerm02.isChecked() && agreeTerm03.isChecked()) {
                        agreeAll.setButtonDrawable(R.drawable.icon_check);
                    }
                } else {
                    agreeAll.setButtonDrawable(R.drawable.icon_check_dis);
                    agreeTerm04.setButtonDrawable(R.drawable.icon_check_dis);
                }
            }
        }
    }
}