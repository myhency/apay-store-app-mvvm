package com.autoever.apay_store_app.ui.payment.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_store_app.data.model.api.PaymentHistoryResponse;
import com.autoever.apay_store_app.databinding.ItemPaymentHistoryBinding;
import com.autoever.apay_store_app.databinding.ItemPaymentHistoryEmptyViewBinding;
import com.autoever.apay_store_app.ui.base.BaseViewHolder;

import java.util.List;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<PaymentHistoryResponse.PaymentHistory.Content> mPaymentHistoryResponseList;

    private PaymentHistoryListener mListener;
    private OnItemClickInterface onItemClickInterface;

    public PaymentHistoryAdapter(List<PaymentHistoryResponse.PaymentHistory.Content> mPaymentHistoryResponseList) {
        this.mPaymentHistoryResponseList = mPaymentHistoryResponseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemPaymentHistoryBinding paymentHistoryBinding = ItemPaymentHistoryBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                );
                return new PaymentHistoryViewHolder(paymentHistoryBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemPaymentHistoryEmptyViewBinding emptyViewBinding = ItemPaymentHistoryEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                );
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<PaymentHistoryResponse.PaymentHistory.Content> contentList) {
        mPaymentHistoryResponseList.addAll(contentList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPaymentHistoryResponseList.clear();
    }

    public void setListener(PaymentHistoryListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.OnBind(position);
    }

    @Override
    public int getItemCount() {
        if (mPaymentHistoryResponseList != null && mPaymentHistoryResponseList.size() > 0) {
            return mPaymentHistoryResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mPaymentHistoryResponseList != null && !mPaymentHistoryResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else if(mPaymentHistoryResponseList.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public interface PaymentHistoryListener {

        void onRetryClick();
    }

    public void onItemClick(OnItemClickInterface onItemClickInterface) {
        this.onItemClickInterface = onItemClickInterface;
    }

    public interface OnItemClickInterface {
        void execute(Long paymentHistoryId, String paymentStatus);
    }

    public class PaymentHistoryViewHolder extends BaseViewHolder implements PaymentHistoryItemViewModel.PaymentHistoryListener {

        private ItemPaymentHistoryBinding mBinding;
        private PaymentHistoryItemViewModel mPaymentHistoryItemViewModel;

        public PaymentHistoryViewHolder(ItemPaymentHistoryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void OnBind(int position) {
            final PaymentHistoryResponse.PaymentHistory.Content content = mPaymentHistoryResponseList.get(position);
            mPaymentHistoryItemViewModel = new PaymentHistoryItemViewModel(content, this);
            mBinding.setViewModel(mPaymentHistoryItemViewModel);

            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(Long paymentHistoryId, String paymentStatus) {
            onItemClickInterface.execute(paymentHistoryId, paymentStatus);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements HistoryEmptyItemViewModel.HistoryEmptyItemViewModelListener {

        private ItemPaymentHistoryEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemPaymentHistoryEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void OnBind(int position) {
            HistoryEmptyItemViewModel emptyItemViewModel = new HistoryEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}
