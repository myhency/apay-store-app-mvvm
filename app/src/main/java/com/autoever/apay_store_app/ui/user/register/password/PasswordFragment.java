package com.autoever.apay_store_app.ui.user.register.password;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentPasswordBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.ui.user.register.RegisterNavigator;
import com.autoever.apay_store_app.ui.user.register.RegisterViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;


public class PasswordFragment extends BaseFragment<FragmentPasswordBinding, RegisterViewModel> implements RegisterNavigator {

    public static final String TAG = PasswordFragment.class.getSimpleName();

    private FragmentPasswordBinding mFragmentPasswordBinding;

    @Inject
    ViewModelProviderFactory factory;

    private RegisterViewModel mPasswordViewModel;

    public static PasswordFragment newInstance(String whatToDo) {
        Bundle args = new Bundle();
        args.putString("whatToDo", whatToDo);
        PasswordFragment fragment = new PasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_password;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterViewModel.class);
        return mPasswordViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPasswordBinding = getViewDataBinding();

        setup();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setup() {
        //하단 버튼 설정
        if(getArguments().getString("whatToDo").equals("check")
                || getArguments().getString("whatToDo").equals("register_new"))
            mFragmentPasswordBinding.skipButton.setVisibility(View.GONE);

        //건너뛰기 버튼 설정
        mFragmentPasswordBinding.skipButton.setOnClickListener(v -> {
            JSONObject data = new JSONObject();
            try {
                data.put("whatToDo", "skip");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            getBaseActivity().onFragmentDetached(TAG);
        });

        //numeric keypad 리스너 설정
        PasswordFragment.NumericKeypadListener listener = new PasswordFragment.NumericKeypadListener();
        final ArrayList<TextView> numericButtons = new ArrayList<>(Arrays.asList(
                mFragmentPasswordBinding.button0,
                mFragmentPasswordBinding.button1,
                mFragmentPasswordBinding.button2,
                mFragmentPasswordBinding.button3,
                mFragmentPasswordBinding.button4,
                mFragmentPasswordBinding.button5,
                mFragmentPasswordBinding.button6,
                mFragmentPasswordBinding.button7,
                mFragmentPasswordBinding.button8,
                mFragmentPasswordBinding.button9
        ));

        for (TextView button : numericButtons) {
            button.setOnClickListener(listener);
        }

        final ArrayList<TextView> functionButtons = new ArrayList<>(Arrays.asList(
                mFragmentPasswordBinding.buttonDelete,
                mFragmentPasswordBinding.buttonArrange,
                mFragmentPasswordBinding.confirmButton
        ));

        for (TextView button : functionButtons) {
            button.setOnClickListener(listener);
        }

        //button별로 랜덤 넘버 할당.
        shuffleNumbers(numericButtons);

        //사용자가 비번을 입력할 때 이벤트 처리
        final ArrayList<ImageView> passwordCharList = new ArrayList<ImageView>(Arrays.asList(
                mFragmentPasswordBinding.passwordChar01,
                mFragmentPasswordBinding.passwordChar02,
                mFragmentPasswordBinding.passwordChar03,
                mFragmentPasswordBinding.passwordChar04,
                mFragmentPasswordBinding.passwordChar05,
                mFragmentPasswordBinding.passwordChar06
        ));

        mFragmentPasswordBinding.passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //사용자가 글자를 쓸 때 쓴 부분을 파란색칸으로 채운다.
                int passwordLength = s.toString().length();
                for (int i = 0; i < passwordLength; i++) {
                    passwordCharList.get(i).setImageResource(R.drawable.ic_bluecircle);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //사용자가 글자를 지울 때 지운 부분을 빈칸으로 채운다.
                int passwordLength = s.length();
                for (int i = passwordLength; i < passwordCharList.size(); i++) {
                    passwordCharList.get(i).setImageResource(R.drawable.ic_greycircle);
                }
            }
        });

        mFragmentPasswordBinding.passwordEdit.setKeyListener(null);
        mFragmentPasswordBinding.passwordEdit.setOnTouchListener((v, event) -> {
            Log.d("debug", "event:" + event.getAction());
            return false;
        });
    }

    @SuppressLint("SetTextI18n")
    private void shuffleNumbers(ArrayList<TextView> numericButtons) {
        Integer[] randomNumbers = new Integer[10];
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = i;
        }
        Collections.shuffle(Arrays.asList(randomNumbers));

        for (int i = 0; i < numericButtons.size(); i++) {
            numericButtons.get(i).setText(randomNumbers[i].toString());
        }
    }

    private void reArrangeButtons () {
        final ArrayList<TextView> numericButtons = new ArrayList<>(Arrays.asList(
                mFragmentPasswordBinding.button0,
                mFragmentPasswordBinding.button1,
                mFragmentPasswordBinding.button2,
                mFragmentPasswordBinding.button3,
                mFragmentPasswordBinding.button4,
                mFragmentPasswordBinding.button5,
                mFragmentPasswordBinding.button6,
                mFragmentPasswordBinding.button7,
                mFragmentPasswordBinding.button8,
                mFragmentPasswordBinding.button9
        ));
        shuffleNumbers(numericButtons);
    }

    //숫자키패드용 리스너
    class NumericKeypadListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_delete:
                    int length = mFragmentPasswordBinding.passwordEdit.getText().length();
                    if (length > 0) mFragmentPasswordBinding.passwordEdit.getText().delete(length - 1, length);
                    break;
                case R.id.button_arrange:
                    reArrangeButtons();
                    break;
                case R.id.button0:
                case R.id.button1:
                case R.id.button2:
                case R.id.button3:
                case R.id.button4:
                case R.id.button5:
                case R.id.button6:
                case R.id.button7:
                case R.id.button8:
                case R.id.button9:
                    TextView button = (TextView) v;
                    mFragmentPasswordBinding.passwordEdit.append(button.getText().toString());
                    break;
                case R.id.confirm_button:
                    //사용자가 6자리 이하의 숫자를 입력하고 확인버튼을 눌렀을 때 Toast로 알려준다.
                    if (mFragmentPasswordBinding.passwordEdit.getText().toString().length() < 6) {
                        Animation animationShake = AnimationUtils.loadAnimation(getBaseActivity(), R.anim.shake);
                        mFragmentPasswordBinding.passwordInput.startAnimation(animationShake);
                        Toast.makeText(
                                getBaseActivity(),
                                "숫자 6자리를 입력해야 합니다.",
                                Toast.LENGTH_SHORT
                        ).show();
                        return;
                    }

                    JSONObject data = new JSONObject();
                    try {
                        data.put("password", mFragmentPasswordBinding.passwordEdit.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getBaseActivity().onReceivedMessageFromFragment(TAG, data);
                    getBaseActivity().onFragmentDetached(TAG);
                    break;
            }
        }
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

    }

}