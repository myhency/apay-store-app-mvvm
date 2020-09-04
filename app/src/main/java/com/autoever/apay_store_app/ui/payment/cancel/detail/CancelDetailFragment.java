package com.autoever.apay_store_app.ui.payment.cancel.detail;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentCancelDetailBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class CancelDetailFragment extends BaseFragment<FragmentCancelDetailBinding, CancelDetailViewModel> implements CancelDetailNavigator {

    public static final String TAG = CancelDetailFragment.class.getSimpleName();
    private FragmentCancelDetailBinding mFragmentCancelDetailBinding;



    @Inject
    ViewModelProviderFactory factory;

    private CancelDetailViewModel mCancelDetailViewModel;

    public static CancelDetailFragment newInstance(Long paymentId) {

        Bundle args = new Bundle();
        args.putLong("paymentId", paymentId);
        CancelDetailFragment fragment = new CancelDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cancel_detail;
    }

    @Override
    public CancelDetailViewModel getViewModel() {
        mCancelDetailViewModel = ViewModelProviders.of(this, factory)
                .get(CancelDetailViewModel.class);
        return mCancelDetailViewModel;
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
        mFragmentCancelDetailBinding = getViewDataBinding();
        mCancelDetailViewModel.setNavigator(this);
        setup();

        mCancelDetailViewModel.doGetPaymentDetail(getArguments().getLong("paymentId"));
    }

    private void setup() {
        mFragmentCancelDetailBinding.finishTextview.setOnClickListener(v -> {
            mCancelDetailViewModel.doPaymentRefundDoCall();
        });
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void goNext() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}