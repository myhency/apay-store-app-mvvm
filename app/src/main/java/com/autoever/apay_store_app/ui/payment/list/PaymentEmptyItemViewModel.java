package com.autoever.apay_store_app.ui.payment.list;

public class PaymentEmptyItemViewModel {

    private PaymentEmptyItemViewModelListener mListener;

    public PaymentEmptyItemViewModel(PaymentEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() { mListener.onRetryClick(); }

    public interface PaymentEmptyItemViewModelListener {

        void onRetryClick();
    }
}
