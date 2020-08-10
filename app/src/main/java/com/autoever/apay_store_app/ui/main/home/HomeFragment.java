package com.autoever.apay_store_app.ui.main.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.error.ANError;
import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentHomeBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.ui.payment.PaymentActivity;
import com.autoever.apay_store_app.ui.payment.history.PaymentHistoryAdapter;
import com.autoever.apay_store_app.ui.payment.scanner.CustomScannerActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private final static int QR_CODE_SCANNED = 1;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    PaymentHistoryAdapter mPaymentHistoryAdapter;

    private HomeViewModel mHomeViewModel;

    private FragmentHomeBinding mFragmentHomeBinding;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        mHomeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        return mHomeViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding = getViewDataBinding();
        mHomeViewModel.setNavigator(this);

        setup();

        fetchPaymentMonthlyHistoryList();
    }

    private void setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentHomeBinding.txRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentHomeBinding.txRecyclerView.setAdapter(mPaymentHistoryAdapter);

        mFragmentHomeBinding.purchaseButton.setOnClickListener(v -> {
            openCustomScanner();
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String today = simpleDateFormat.format(new Date());

        LocalDate lastDateOfThisMonth = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        lastDateOfThisMonth = lastDateOfThisMonth.withDayOfMonth(
                lastDateOfThisMonth.getMonth().length(lastDateOfThisMonth.isLeapYear()));
        LocalDate firstDateOfThisMonth = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        firstDateOfThisMonth = firstDateOfThisMonth.withDayOfMonth(1);

        mFragmentHomeBinding.currentMonthPeriod.setText(firstDateOfThisMonth.toString() + " ~ " + lastDateOfThisMonth.toString());
    }

    private void openCustomScanner() {
        Log.d("debug", "openCustomScannerActivity");
        Intent intent = CustomScannerActivity.newIntent(getBaseActivity());
        startActivityForResult(intent, QR_CODE_SCANNED);
    }

    private void fetchPaymentMonthlyHistoryList() {
        mHomeViewModel.fetchPaymentMonthlyHistoryData(
                1,
                2,
                new Date(),
                null,
                0,
                10
        );
    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug",anError.getMessage());
    }

    @Override
    public void openPaymentActivity(String shopCode) {
        Log.d("debug", "openPaymentActivity");
        Intent intent = PaymentActivity.newIntent(getBaseActivity());
        intent.putExtra("shopCode", shopCode);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("debug", data.getStringExtra("shopCode"));
        switch (requestCode) {
            case QR_CODE_SCANNED:
                switch (resultCode) {
                    case RESULT_OK:  //사용자 앱에서 Dynamic QR Code 를 읽어 Activity 에게 전달.
                        String shopCode = data.getStringExtra("shopCode");
                        openPaymentActivity(shopCode);
                    default:
                        break;
                }
            default:
                break;
        }

//        getBaseActivity().onReceivedMessageFromFragment(TAG, );

    }


}