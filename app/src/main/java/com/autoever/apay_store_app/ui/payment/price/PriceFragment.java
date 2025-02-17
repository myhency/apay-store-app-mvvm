package com.autoever.apay_store_app.ui.payment.price;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentPriceBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class PriceFragment extends BaseFragment<FragmentPriceBinding, PriceViewModel> implements PriceNavigator {

    public static final String TAG = PriceFragment.class.getSimpleName();

    private FragmentPriceBinding mFragmentPriceBinding;

    @Inject
    ViewModelProviderFactory factory;
    private PriceViewModel mPriceViewModel;

    public static PriceFragment newInstance(String shopName) {
        Bundle args = new Bundle();
        args.putString("shopName", shopName);
        PriceFragment fragment = new PriceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_price;
    }

    @Override
    public PriceViewModel getViewModel() {
        mPriceViewModel = ViewModelProviders.of(this, factory).get(PriceViewModel.class);
        return mPriceViewModel;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPriceBinding = getViewDataBinding();

        setup();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setup() {
        //커서위치를 input text 로 이동.
        mFragmentPriceBinding.cardPaymentEdittext.requestFocus();

//        mFragmentPriceBinding.cardPaymentEdittext.setSelection(
//                mFragmentPriceBinding.cardPaymentEdittext.getText().length());

        //터치이벤트를 소모해서 키보드를 뜨지 않게 함.
        mFragmentPriceBinding.cardPaymentEdittext.setOnTouchListener((v, event) -> true);

        mFragmentPriceBinding.buttonDelete.setOnClickListener(v -> {
            int length = mFragmentPriceBinding.cardPaymentEdittext.getText().length();
            if (length > 0)
                mFragmentPriceBinding.cardPaymentEdittext.getText().delete(length - 1, length);
        });

        mFragmentPriceBinding.button0.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("0"));
        mFragmentPriceBinding.button1.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("1"));
        mFragmentPriceBinding.button2.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("2"));
        mFragmentPriceBinding.button3.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("3"));
        mFragmentPriceBinding.button4.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("4"));
        mFragmentPriceBinding.button5.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("5"));
        mFragmentPriceBinding.button6.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("6"));
        mFragmentPriceBinding.button7.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("7"));
        mFragmentPriceBinding.button8.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("8"));
        mFragmentPriceBinding.button9.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("9"));
        mFragmentPriceBinding.button00.setOnClickListener(v -> mFragmentPriceBinding.cardPaymentEdittext.append("00"));

        mFragmentPriceBinding.price1000.setOnClickListener(v -> {

            Long currentInputBalance;
            if (mFragmentPriceBinding.cardPaymentEdittext.getText().toString().equals(""))
                currentInputBalance = 0L;
            else
                currentInputBalance = Long.valueOf(mFragmentPriceBinding.cardPaymentEdittext.getText().toString().replaceAll("[%s,.]", ""));
            currentInputBalance = currentInputBalance + 1000L;
            mFragmentPriceBinding.cardPaymentEdittext.setText(String.valueOf(currentInputBalance));
        });

        mFragmentPriceBinding.price5000.setOnClickListener(v -> {

            Long currentInputBalance;
            if (mFragmentPriceBinding.cardPaymentEdittext.getText().toString().equals(""))
                currentInputBalance = 0L;
            else
                currentInputBalance = Long.valueOf(mFragmentPriceBinding.cardPaymentEdittext.getText().toString().replaceAll("[%s,.]", ""));
            currentInputBalance = currentInputBalance + 5000L;
            mFragmentPriceBinding.cardPaymentEdittext.setText(String.valueOf(currentInputBalance));
        });

        mFragmentPriceBinding.price10000.setOnClickListener(v -> {

            Long currentInputBalance;
            if (mFragmentPriceBinding.cardPaymentEdittext.getText().toString().equals(""))
                currentInputBalance = 0L;
            else
                currentInputBalance = Long.valueOf(mFragmentPriceBinding.cardPaymentEdittext.getText().toString().replaceAll("[%s,.]", ""));
            currentInputBalance = currentInputBalance + 10000L;
            mFragmentPriceBinding.cardPaymentEdittext.setText(String.valueOf(currentInputBalance));
        });

        mFragmentPriceBinding.price50000.setOnClickListener(v -> {

            Long currentInputBalance;
            if (mFragmentPriceBinding.cardPaymentEdittext.getText().toString().equals(""))
                currentInputBalance = 0L;
            else
                currentInputBalance = Long.valueOf(mFragmentPriceBinding.cardPaymentEdittext.getText().toString().replaceAll("[%s,.]", ""));
            currentInputBalance = currentInputBalance + 50000L;
            mFragmentPriceBinding.cardPaymentEdittext.setText(String.valueOf(currentInputBalance));
        });

        mFragmentPriceBinding.balanceCurrency.setOnClickListener(v -> {
            if (mFragmentPriceBinding.balanceCurrency.getText().toString().equals("P")) return;
            mFragmentPriceBinding.cardPaymentEdittext.setText("");
        });

        mFragmentPriceBinding.cardPaymentEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    mFragmentPriceBinding.balanceCurrency.setText("P");
                    return;
                }

                mFragmentPriceBinding.balanceCurrency.setText("X");

                //원화화폐단위로 포맷하여 보여줌
                mFragmentPriceBinding.cardPaymentEdittext.removeTextChangedListener(this);
                String cleanString = s.toString().replaceAll("[%s,.]", "");
                mFragmentPriceBinding.cardPaymentEdittext.setText(CommonUtils.formatToKRW(cleanString));
                mFragmentPriceBinding.cardPaymentEdittext.addTextChangedListener(this);
            }
        });

        mFragmentPriceBinding.finishTextview.setOnClickListener(v -> {
            goNext();
        });
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void goNext() {
        Log.d("debug", "Price confirmed");
        JSONObject data = new JSONObject();
        try {
            data.put("price", mFragmentPriceBinding.cardPaymentEdittext.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getBaseActivity().onReceivedMessageFromFragment(TAG, data);
        getBaseActivity().onFragmentDetached(TAG);
    }
}