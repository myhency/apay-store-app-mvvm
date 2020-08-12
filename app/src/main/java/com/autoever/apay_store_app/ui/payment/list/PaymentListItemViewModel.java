package com.autoever.apay_store_app.ui.payment.list;

import androidx.databinding.ObservableField;

import com.autoever.apay_store_app.data.model.api.PaymentListResponse;
import com.autoever.apay_store_app.ui.payment.common.PaymentStatus;
import com.autoever.apay_store_app.utils.CommonUtils;

import java.text.SimpleDateFormat;

public class PaymentListItemViewModel {

    public final ObservableField<String> paymentId;

    public final ObservableField<String> amount;

    public final ObservableField<String> date;

    public final ObservableField<String> paymentStatus;

    public final PaymentListListener mListener;

    private final PaymentListResponse.PaymentList.Content mList;

    public PaymentListItemViewModel(PaymentListResponse.PaymentList.Content list, PaymentListItemViewModel.PaymentListListener listener) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        this.mList = list;
        this.mListener = listener;
        paymentId = new ObservableField<>(String.valueOf(mList.getPaymentId()));
        amount = new ObservableField<>(CommonUtils.formatToKRW(String.valueOf(mList.getAmount())) + " P");
        date = new ObservableField<>(simpleDateFormat.format(mList.getCreatedDate()));
        paymentStatus = new ObservableField<>(PaymentStatus.find(mList.getPaymentStatus()).getDisplayValue());
    }

    public void onItemClick() {
        mListener.onItemClick(mList.getPaymentId(), mList.getPaymentStatus());
    }

    public interface PaymentListListener {

        void onItemClick(Long paymentId, String paymentStatus);
    }
}
