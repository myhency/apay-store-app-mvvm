package com.autoever.apay_store_app.ui.main.home;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.autoever.apay_store_app.ui.payment.history.PaymentHistoryAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    @Provides
    PaymentHistoryAdapter providePaymentHistoryAdapter() {
        return new PaymentHistoryAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(HomeFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}


