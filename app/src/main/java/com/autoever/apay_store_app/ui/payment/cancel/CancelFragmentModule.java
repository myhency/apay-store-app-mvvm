package com.autoever.apay_store_app.ui.payment.cancel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.autoever.apay_store_app.ui.payment.list.PaymentListAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CancelFragmentModule {

    @Provides
    PaymentListAdapter providePaymentListAdapter() {
        return new PaymentListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CancelFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}


