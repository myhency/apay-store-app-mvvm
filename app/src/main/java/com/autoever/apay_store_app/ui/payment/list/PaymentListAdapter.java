package com.autoever.apay_store_app.ui.payment.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_store_app.data.model.api.PaymentListResponse;
import com.autoever.apay_store_app.databinding.ItemPaymentListBinding;
import com.autoever.apay_store_app.databinding.ItemPaymentListEmptyViewBinding;
import com.autoever.apay_store_app.ui.base.BaseViewHolder;

import java.util.List;

public class PaymentListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<PaymentListResponse.PaymentList.Content> mPaymentListResponseList;

    private PaymentListListener mListener;
    private OnItemClickInterface onItemClickInterface;

    public PaymentListAdapter(List<PaymentListResponse.PaymentList.Content> mPaymentListResponseList) {
        this.mPaymentListResponseList = mPaymentListResponseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemPaymentListBinding paymentListBinding = ItemPaymentListBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                );
                return new PaymentListViewHolder(paymentListBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemPaymentListEmptyViewBinding emptyViewBinding = ItemPaymentListEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                );
                return new EmptyViewHolder(emptyViewBinding);

        }
    }

    public void addItems(List<PaymentListResponse.PaymentList.Content> contentList) {
        mPaymentListResponseList.addAll(contentList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPaymentListResponseList.clear();
    }

    public void setListener(PaymentListListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.OnBind(position);
    }

    @Override
    public int getItemCount() {
        if(mPaymentListResponseList != null && mPaymentListResponseList.size() > 0) {
            return mPaymentListResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mPaymentListResponseList != null && !mPaymentListResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else if (mPaymentListResponseList.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public class PaymentListViewHolder extends BaseViewHolder implements PaymentListItemViewModel.PaymentListListener {

        private ItemPaymentListBinding mBinding;
        private PaymentListItemViewModel mPaymentListItemViewModel;

        public PaymentListViewHolder(ItemPaymentListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void OnBind(int position) {
            final PaymentListResponse.PaymentList.Content content = mPaymentListResponseList.get(position);
            mPaymentListItemViewModel = new PaymentListItemViewModel(content, this);
            mBinding.setViewModel(mPaymentListItemViewModel);

            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(Long paymentId, String paymentStatus) {
            onItemClickInterface.execute(paymentId, paymentStatus);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements PaymentEmptyItemViewModel.PaymentEmptyItemViewModelListener {

        private ItemPaymentListEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemPaymentListEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void OnBind(int position) {
            PaymentEmptyItemViewModel emptyItemViewModel = new PaymentEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public interface PaymentListListener {

        void onRetryClick();
    }

    public void onItemClick(OnItemClickInterface onItemClickInterface) {
        this.onItemClickInterface = onItemClickInterface;
    }

    public interface OnItemClickInterface {
        void execute(Long paymentId, String paymentStatus);
    }
}


