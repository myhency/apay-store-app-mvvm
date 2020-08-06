package com.autoever.apay_store_app.ui.payment.history;

import androidx.databinding.ObservableField;

import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.ui.payment.common.PaymentStatus;
import com.autoever.apay_store_app.utils.CommonUtils;

import java.text.SimpleDateFormat;

public class PaymentHistoryItemViewModel {

    public final ObservableField<String> paymentId;

    public final ObservableField<String> amount;

    public final ObservableField<String> date;

    public final ObservableField<String> paymentStatus;

    public final PaymentHistoryListener mListener;

    private final PaymentHistoryResponse.PaymentHistory.Content mHistory;

    public PaymentHistoryItemViewModel(PaymentHistoryResponse.PaymentHistory.Content history, PaymentHistoryListener listener) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        this.mHistory = history;
        this.mListener = listener;
        paymentId = new ObservableField<>(String.valueOf(mHistory.getPaymentId()));
        amount = new ObservableField<>(CommonUtils.formatToKRW(String.valueOf(mHistory.getAmount())) + " P");
        date = new ObservableField<>(simpleDateFormat.format(mHistory.getCreatedDate()));
        paymentStatus = new ObservableField<>(PaymentStatus.find(mHistory.getPaymentStatus()).getDisplayValue());
    }

    public void onItemClick() {
        mListener.onItemClick(mHistory.getPaymentHistoryId(), mHistory.getPaymentStatus());
    }

    public interface PaymentHistoryListener {

        void onItemClick(Long paymentHistoryId, String paymentStatus);
    }
}
