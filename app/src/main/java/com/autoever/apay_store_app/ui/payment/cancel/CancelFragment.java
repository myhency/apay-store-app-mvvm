package com.autoever.apay_store_app.ui.payment.cancel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.FragmentCancelBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class CancelFragment extends BaseFragment<FragmentCancelBinding, CancelViewModel> implements CancelNavigator {

    public static final String TAG = CancelFragment.class.getSimpleName();

    private FragmentCancelBinding mFragmentCancelBinding;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    LinearLayoutManager mLayoutManager;

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
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCancelBinding = getViewDataBinding();
        mCancelViewModel.setNavigator(this);
        mCancelViewModel.fetchPaymentCancelRequestData(1,2, "refundReady", 0, 10);
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}