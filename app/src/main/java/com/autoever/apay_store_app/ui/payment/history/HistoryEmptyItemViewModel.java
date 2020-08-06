package com.autoever.apay_store_app.ui.payment.history;

public class HistoryEmptyItemViewModel {

    private HistoryEmptyItemViewModelListener mListener;

    public HistoryEmptyItemViewModel(HistoryEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() { mListener.onRetryClick(); }

    public interface HistoryEmptyItemViewModelListener {

        void onRetryClick();
    }
}
