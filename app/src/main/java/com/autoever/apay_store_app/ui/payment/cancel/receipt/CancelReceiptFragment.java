package com.autoever.apay_store_app.ui.payment.cancel.receipt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.data.model.api.PaymentRefundDoResponse;
import com.autoever.apay_store_app.databinding.FragmentCancelReceiptBinding;
import com.autoever.apay_store_app.ui.base.BaseFragment;
import com.autoever.apay_store_app.utils.CommonUtils;

import java.text.SimpleDateFormat;

import javax.inject.Inject;


public class CancelReceiptFragment extends BaseFragment<FragmentCancelReceiptBinding, CancelReceiptViewModel> {

    public static final String TAG = CancelReceiptFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private FragmentCancelReceiptBinding mFragmentCancelReceiptBinding;
    private CancelReceiptViewModel mCancelReceiptViewModel;

    public static CancelReceiptFragment newInstance(PaymentRefundDoResponse paymentRefundDoResponse) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd(E) HH:mm:ss");
        Bundle args = new Bundle();
        //TODO. 스토어 아이디가 아닌 이름으로 받을 것.
        args.putString("paymentId", paymentRefundDoResponse.getData().getPaymentId().toString());
        args.putString("storeId", paymentRefundDoResponse.getData().getStoreId().toString());
        args.putString("createdDate", simpleDateFormat.format(paymentRefundDoResponse.getData().getCreatedDate()));
        args.putString("amount", CommonUtils.formatToKRW(String.valueOf(paymentRefundDoResponse.getData().getAmount())) + " P");
        //TODO. 남은 잔액 받을 것.
        args.putString("balanceLeft", "12,000P");
        CancelReceiptFragment fragment = new CancelReceiptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cancel_receipt;
    }

    @Override
    public CancelReceiptViewModel getViewModel() {
        mCancelReceiptViewModel = ViewModelProviders.of(this, factory)
                .get(CancelReceiptViewModel.class);
        return mCancelReceiptViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCancelReceiptBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        mFragmentCancelReceiptBinding.purchaseAmount.setText(getArguments().getString("amount"));
        mFragmentCancelReceiptBinding.paymentId.setText(getArguments().getString("paymentId"));
        mFragmentCancelReceiptBinding.cancelDate.setText(getArguments().getString("createdDate"));

        mFragmentCancelReceiptBinding.finishTextview.setOnClickListener(v -> {
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}