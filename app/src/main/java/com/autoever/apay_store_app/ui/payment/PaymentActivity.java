package com.autoever.apay_store_app.ui.payment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.data.model.api.PaymentRefundDoResponse;
import com.autoever.apay_store_app.databinding.ActivityPaymentBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.payment.cancel.CancelFragment;
import com.autoever.apay_store_app.ui.payment.cancel.detail.CancelDetailFragment;
import com.autoever.apay_store_app.ui.payment.cancel.receipt.CancelReceiptFragment;
import com.autoever.apay_store_app.ui.payment.confirm.PriceConfirmFragment;
import com.autoever.apay_store_app.ui.payment.price.PriceFragment;
import com.autoever.apay_store_app.ui.payment.receipt.ReceiptFragment;
import com.autoever.apay_store_app.utils.AppConstants;
import com.autoever.apay_store_app.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.HttpException;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentViewModel> implements PaymentNavigator, HasSupportFragmentInjector {

    private String storeName = "";
    private int price = 0;

    private FragmentManager mFragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityPaymentBinding mActivityPaymentBinding;
    private PaymentViewModel mPaymentViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public PaymentViewModel getViewModel() {
        mPaymentViewModel = ViewModelProviders.of(this, factory)
                .get(PaymentViewModel.class);
        return mPaymentViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "PaymentActivity onCreate");
        mActivityPaymentBinding = getViewDataBinding();
        mPaymentViewModel.setNavigator(this);

        setup();

        if (getIntent().getIntExtra("whatToOpen", 100) == AppConstants.PAYMENT_LIST) {
            openCancelFragment();
        } else {
            openPriceFragment(getIntent().getStringExtra("shopCode"));
        }
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityPaymentBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityPaymentBinding.toolbarTitle.setText("결제");
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void handleError(Throwable throwable) {
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            case 400:
                try {
                    if(httpException.response().errorBody().string().contains("ExpiredQrTokenException")) {
                        //여기서 다이얼로그를 띄워준다.
                        // custom dialog
                        final Dialog dialog = new Dialog(this);
                        dialog.setContentView(R.layout.qr_expired_dialog);

                        Button okButton = dialog.findViewById(R.id.ok_button);

                        okButton.setOnClickListener(v1 -> {
                            dialog.dismiss();
                            finish();
                        });

                        dialog.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void openPriceFragment(String shopCode) {
        //금액입력화면으로 이동.
        Log.d("debug", "openPriceFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PriceFragment.newInstance(shopCode), PriceFragment.TAG)
                .addToBackStack(PriceFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openPriceConfirmFragment(String shopCode, int price) {
        Log.d("debug", "openPriceConfirmFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PriceConfirmFragment.newInstance(storeName, price), PriceConfirmFragment.TAG)
                .addToBackStack(PriceConfirmFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("debug", "onOptionsItemSelected:" + item.toString());
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                List<Fragment> fragments = mFragmentManager.getFragments();
                String fragmentTag = fragments.get(fragments.size() - 1).getTag();
                Log.d("debug", "current fragment: "+fragmentTag);
                switch (fragmentTag) {
                    case "PriceFragment":
                    case "CancelFragment":
                        finish();
                        break;
                    case "PriceConfirmFragment":
                    case "AuthFragment":
                    case "CancelDetailFragment":
                        removeFragment(fragmentTag);
                        break;
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentDetached(String tag) {
        Log.d("debug", "onFragmentDetached: " + tag);

        switch (tag) {
            case "PriceFragment":
                openPriceConfirmFragment(storeName, price);
                break;
            case "PriceConfirmFragment":
                doPaymentReady();
                break;
            case "CancelDetailFragment":
                removeFragment(tag);
                break;
            case "CancelReceiptFragment":
                removeFragment(tag);
                mActivityPaymentBinding.toolbar.setVisibility(View.VISIBLE);
                mActivityPaymentBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
                break;
//            case "AuthFragment":
//                doPaymentReady();
//                break;
        }
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        try {
            switch (tag) {
                case "PriceFragment":
                    price = CommonUtils.parseToInt(message.getString("price"));
                    break;
                case "CancelFragment":
                    openCancelDetailFragment(message.getLong("paymentId"));
                    break;
                case "CancelDetailFragment":
                    openCancelReceiptFragment((PaymentRefundDoResponse)message.get("paymentRefundDoResponse"));
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openCancelReceiptFragment(PaymentRefundDoResponse paymentRefundDoResponse) {
        //결제취소승인 완료 화면으로 이동.
        Log.d("debug", "openPaymentRefundReadyReceiptFragment");
        mActivityPaymentBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityPaymentBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CancelReceiptFragment.newInstance(paymentRefundDoResponse), CancelReceiptFragment.TAG)
                .addToBackStack(CancelReceiptFragment.TAG)
                .commit();
    }

    @Override
    public void openCancelDetailFragment(long paymentId) {
        Log.d("debug", "openCancelDetailFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CancelDetailFragment.newInstance(paymentId), CancelDetailFragment.TAG)
                .addToBackStack(CancelDetailFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void getQrStoreDynamicData(String parsedQrString) {

    }

    @Override
    public void doPaymentReady() {
        Log.d("debug", "doPaymentReady");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mPaymentViewModel.doPaymentReadyUserDynamic(
                Long.valueOf(price),
                String.valueOf(timestamp.getTime()),
                getIntent().getStringExtra("shopCode")
        );
    }

    @Override
    public void openReceiptFragment(String storeName, Date createdDate, int amount, int userBalance) {
        mActivityPaymentBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityPaymentBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, ReceiptFragment.newInstance(storeName, createdDate, amount, userBalance), ReceiptFragment.TAG)
                .addToBackStack(ReceiptFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openCancelFragment() {
        mActivityPaymentBinding.toolbarTitle.setText("결제취소 요청내역");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CancelFragment.newInstance(), CancelFragment.TAG)
                .addToBackStack(CancelFragment.TAG)
                .commitAllowingStateLoss();
    }

    public void removeFragment(String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        mFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .remove(fragment)
                .commit();
    }
}
