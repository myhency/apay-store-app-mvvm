package com.autoever.apay_store_app.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.data.model.api.PaymentListResponse;
import com.autoever.apay_store_app.ui.payment.history.PaymentHistoryAdapter;
import com.autoever.apay_store_app.ui.payment.list.PaymentListAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter({"adapter"})
    public static void addPaymentHistoryItems(RecyclerView recyclerView, List<PaymentHistoryResponse.PaymentHistory.Content> contentList) {
        PaymentHistoryAdapter adapter = (PaymentHistoryAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(contentList);
        }
    }

    @BindingAdapter({"list_adapter"})
    public static void addPaymentListItems(RecyclerView recyclerView, List<PaymentListResponse.PaymentList.Content> contentList) {
        PaymentListAdapter adapter = (PaymentListAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(contentList);
        }
    }
}
