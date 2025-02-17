package com.autoever.apay_store_app.ui.main.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.error.ANError;
import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentHomeBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.ui.payment.PaymentActivity;
import com.autoever.apay_store_app.ui.payment.history.PaymentHistoryAdapter;
import com.autoever.apay_store_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_store_app.utils.AppConstants;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private final static int QR_CODE_SCANNED = 1;
    private static int PAGE_NO = 0;
    private final static int PAGE_SIZE = 20;

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

        fetchPaymentMonthlyHistoryList(PAGE_NO);
    }

    private void setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentHomeBinding.txRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentHomeBinding.txRecyclerView.setAdapter(mPaymentHistoryAdapter);

        //결제하기 버튼 누를시 실행.
        mFragmentHomeBinding.purchaseButton.setOnClickListener(v -> {
            openCustomScanner();
        });

        //이달의 거래내역의 날짜 출력.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String today = simpleDateFormat.format(new Date());
        LocalDate lastDateOfThisMonth = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        lastDateOfThisMonth = lastDateOfThisMonth.withDayOfMonth(
                lastDateOfThisMonth.getMonth().length(lastDateOfThisMonth.isLeapYear()));
        LocalDate firstDateOfThisMonth = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        firstDateOfThisMonth = firstDateOfThisMonth.withDayOfMonth(1);
        mFragmentHomeBinding.currentMonthPeriod.setText(firstDateOfThisMonth.toString() + " ~ " + lastDateOfThisMonth.toString());

        mFragmentHomeBinding.swipeContainer.setOnRefreshListener(() -> {
            Log.i("debug", "onRefresh called from SwipeRefreshLayout");
            PAGE_NO = 0;
            fetchPaymentMonthlyHistoryList(PAGE_NO);
        });

        //RecyclerView 세팅.
        mFragmentHomeBinding.txRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("debug", "scroll state:" + newState);
                if (!recyclerView.canScrollVertically(1)) {
                    Log.d("debug", "Last");
                    PAGE_NO = PAGE_NO + 1;
                    fetchPaymentMonthlyHistoryList(PAGE_NO);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

                if (firstVisibleItemPosition != 0) {
                    mFragmentHomeBinding.purchaseButton.animate()
                            .translationY(mFragmentHomeBinding.purchaseButton.getHeight())
                            .alpha(0.0f)
                            .setDuration(10)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mFragmentHomeBinding.purchaseButton.setVisibility(View.GONE);
                                }
                            });
                } else {
                    mFragmentHomeBinding.purchaseButton.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setDuration(10)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mFragmentHomeBinding.purchaseButton.setVisibility(View.VISIBLE);
                                }
                            });
                }
            }
        });
    }

    private void openCustomScanner() {
        Log.d("debug", "openCustomScannerActivity");
        Intent intent = CustomScannerActivity.newIntent(getBaseActivity());
        startActivityForResult(intent, QR_CODE_SCANNED);
    }

    private void fetchPaymentMonthlyHistoryList(int pageNo) {
        mHomeViewModel.fetchPaymentMonthlyHistoryData(
                new Date(),
                null,
                pageNo,
                20
        );
    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug", anError.getMessage());
    }

    @Override
    public void openPaymentActivity(int whatToOpen, String shopCode) {
        Log.d("debug", "openPaymentActivity");
        Intent intent = PaymentActivity.newIntent(getBaseActivity());
        intent.putExtra("shopCode", shopCode);
        intent.putExtra("whatToOpen", whatToOpen);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case QR_CODE_SCANNED:
                switch (resultCode) {
                    case RESULT_OK:  //사용자 앱에서 Dynamic QR Code 를 읽어 Activity 에게 전달.
                        Vibrator vibrator = (Vibrator) getBaseActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(20);
                        String shopCode = data.getStringExtra("shopCode");
                        openPaymentActivity(AppConstants.PRICE_INPUT, shopCode);
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("debug", "HomeFragment resume");
        mHomeViewModel.loadUserBalance();
    }

    @Override
    public void onCompleteUpdatePaymentHistoryList() {

        if (mFragmentHomeBinding.swipeContainer.isRefreshing()) {
            mFragmentHomeBinding.swipeContainer.setRefreshing(false);
        }
    }
}