package com.autoever.apay_store_app.ui.payment.cancel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentCancelBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.ui.payment.list.PaymentListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;


public class CancelFragment extends BaseFragment<FragmentCancelBinding, CancelViewModel> implements CancelNavigator, PaymentListAdapter.PaymentListListener  {

    public static final String TAG = CancelFragment.class.getSimpleName();

    private static int PAGE_NO = 0;
    private final static int PAGE_SIZE = 10;

    private FragmentCancelBinding mFragmentCancelBinding;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    PaymentListAdapter mPaymentListAdapter;

    private CancelViewModel mCancelViewModel;

    public static CancelFragment newInstance() {
        Bundle args = new Bundle();
        CancelFragment fragment = new CancelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cancel;
    }

    @Override
    public CancelViewModel getViewModel() {
        mCancelViewModel = ViewModelProviders.of(this, factory).get(CancelViewModel.class);
        return mCancelViewModel;
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
        mFragmentCancelBinding = getViewDataBinding();
        mCancelViewModel.setNavigator(this);

        setup();

        fetchPaymentCancelRequestData(PAGE_NO);
    }

    private void fetchPaymentCancelRequestData(int pageNo) {
        mCancelViewModel.fetchPaymentCancelRequestData(
                1,
                2,
                "refundReady",
                pageNo,
                PAGE_SIZE
        );
    }

    private void setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCancelBinding.useHistoryList.setLayoutManager(mLayoutManager);
        mFragmentCancelBinding.useHistoryList.setAdapter(mPaymentListAdapter);

        mPaymentListAdapter.onItemClick(((paymentId, paymentStatus) -> {
            Log.d("debug", "onItemClick");
            openPaymentListDetail(paymentId, paymentStatus);
        }));

        mFragmentCancelBinding.swipeContainer.setOnRefreshListener(() -> {
            Log.i("debug", "onRefresh called from SwipeRefreshLayout");
            PAGE_NO = 0;
            fetchPaymentCancelRequestData(PAGE_NO);
        });
    }

    @Override
    public void openPaymentListDetail(Long paymentId, String paymentStatus) {
        try {
            JSONObject data = new JSONObject();
            data.put("paymentId", paymentId);
            getBaseActivity().onReceivedMessageFromFragment(TAG, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("debug", "CancelFragment onDetach");
        PAGE_NO = 0;
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onCompleteUpdatePaymentCancelList() {

        if (mFragmentCancelBinding.swipeContainer.isRefreshing()) {
            mFragmentCancelBinding.swipeContainer.setRefreshing(false);
        }
    }
}