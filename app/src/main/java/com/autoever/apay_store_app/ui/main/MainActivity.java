package com.autoever.apay_store_app.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityMainBinding;
import com.autoever.apay_store_app.databinding.NavHeaderMainBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.main.home.HomeFragment;
import com.autoever.apay_store_app.ui.payment.PaymentActivity;
import com.autoever.apay_store_app.ui.user.login.LoginActivity;
import com.autoever.apay_store_app.utils.AppConstants;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, HasSupportFragmentInjector {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;




    private MainViewModel mMainViewModel;
    private ActivityMainBinding mActivityMainBinding;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private FragmentManager mFragmentManager;

    @NotNull
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);

        setup();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        mDrawer = mActivityMainBinding.drawerLayout;
        mToolbar = mActivityMainBinding.toolbar;
        mNavigationView = mActivityMainBinding.navView;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        openHomeFragment();
    }

    private void setupNavMenu() {
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, mActivityMainBinding.navView, false);
        mActivityMainBinding.navView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setViewModel(mMainViewModel);

        //menu 우측에 아이콘 삽입.
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            mNavigationView.getMenu().getItem(i).setActionView(R.layout.menu_image);
        }

//        navigationView.setNavigationItemSelectedListener(this);

        //home menu 는 보이지 않게 설정.
        Menu menu = mNavigationView.getMenu();
        MenuItem target = menu.findItem(R.id.nav_home);
        target.setVisible(false);

        mActivityMainBinding.logout.setOnClickListener(v -> {
            //여기서 다이얼로그를 띄워준다.
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.logout_dialog);

            Button okButton = dialog.findViewById(R.id.ok_button);
            Button cancelButton = dialog.findViewById(R.id.cancel_button);

            okButton.setOnClickListener(v1 -> {
                dialog.dismiss();
                mMainViewModel.logout();
            });

            cancelButton.setOnClickListener(v2 -> {
                dialog.dismiss();
            });

            dialog.show();
        });

        mNavigationView.setNavigationItemSelectedListener(item -> {
            mDrawer.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.nav_payment_cancel:
                    openPaymentActivity(AppConstants.PAYMENT_LIST);
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void openHomeFragment() {
        Log.d("debug", "openHomeFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, HomeFragment.newInstance(), HomeFragment.TAG)
                .addToBackStack(HomeFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openLoginActivity() {
        Log.d("debug", "LoginActivity Open");
        Intent intent = LoginActivity.newIntent(MainActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openPaymentActivity(int whatToOpen) {
        Log.d("debug", "openPaymentActivity");
        Intent intent = PaymentActivity.newIntent(MainActivity.this);
        intent.putExtra("whatToOpen", whatToOpen);
        startActivity(intent);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}